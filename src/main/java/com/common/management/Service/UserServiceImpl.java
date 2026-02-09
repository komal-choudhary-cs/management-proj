package com.common.management.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.common.management.dto.CreateUserDto;
import com.common.management.dto.UserAddressDto;
import com.common.management.dto.UserDto;
import com.common.management.entity.UserAddressEntity;
import com.common.management.entity.UserEntity;
import com.common.management.repository.AddressRepository;
import com.common.management.repository.UserRepository;
import com.common.management.validation.AddressValidation;
import com.common.management.validation.UserValidation;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private UserValidation userValidation;
	@Autowired
	private EmailService emailService;
	@Autowired
	private AddressValidation addressValidation;

	private UserDto userToDto(UserEntity users) {
		UserDto userDto = new UserDto();
		userDto.setId(users.getId());
		userDto.setEmail(users.getEmail());
		userDto.setFirstName(users.getFirstName());
		userDto.setLastName(users.getLastName());
		userDto.setEmail(users.getEmail());
		userDto.setStatus(users.getStatus());
		if (users.getUserAddresses() != null) {
			List<UserAddressDto> addressDto = new ArrayList<>();
			for (UserAddressEntity address : users.getUserAddresses()) {
				UserAddressDto userAddressDto = new UserAddressDto();
				userAddressDto.setId(address.getId());
				userAddressDto.setCity(address.getCity());
				userAddressDto.setHouseNumber(address.getHouseNumber());
				userAddressDto.setPincode(address.getPincode());
				userAddressDto.setState(address.getState());
				userAddressDto.setStreetNumber(address.getStreetNumber());
				addressDto.add(userAddressDto);
			}
			userDto.setAddresses(addressDto);
		}
		return userDto;
	}

	@Transactional
	public List<UserDto> createUsers(List<CreateUserDto> createUserDtos) {
		List<UserDto> response = new ArrayList<>();
		for (CreateUserDto createUserDto : createUserDtos) {
			userValidation.validateCreateUser(createUserDto);
			UserEntity user = new UserEntity();
			user.setFirstName(createUserDto.getFirstName());
			user.setLastName(createUserDto.getLastName());
			user.setEmail(createUserDto.getEmail());
			user.setStatus("ACTIVE");
			// UserEntity savedUser = userRepository.save(user);
			// List<UserAddressEntity> addresses = addressRepository.saveAll(addresses);
			List<UserAddressEntity> addresses = new ArrayList<>();
			if (createUserDto.getAddresses() != null && !createUserDto.getAddresses().isEmpty()) {
				for (UserAddressDto addressDto : createUserDto.getAddresses()) {
					addressValidation.validate(addressDto);
					UserAddressEntity address = new UserAddressEntity();
					address.setCity(addressDto.getCity());
					address.setHouseNumber(addressDto.getHouseNumber());
					address.setPincode(addressDto.getPincode());
					address.setState(addressDto.getState());
					address.setStreetNumber(addressDto.getStreetNumber());
					address.setUser(user);
					addresses.add(address);
				}
                addressRepository.saveAll(addresses);
			}
			user.setUserAddresses(addresses);
			UserEntity savedUser = userRepository.save(user);
			Thread myemailThread = new Thread(
					new LoginEmailtask(emailService, savedUser.getEmail(), savedUser.getFirstName()));
			myemailThread.start();
			response.add(userToDto(savedUser));
		}
		return response;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<UserEntity> users = userRepository.findAll();
		List<UserDto> response = new ArrayList<>();
		for (UserEntity user : users) {
			response.add(userToDto(user));
		}
		return response;
	}

	@Override
	public UserDto GetUserById(Long id) {

		UserEntity user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with id: " + id));

		return userToDto(user);
	}

	@Override
	public UserDto DeletUserById(Long id) {
		UserEntity user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("user not found with id" + id));
		user.setStatus("INACTIVE");

		return userToDto(userRepository.save(user));
	}

	@Override
	public List<UserDto> GetUserByStatus(String status, Pageable pageable) {
		if (status == null || status.isEmpty()) {
			status = "ACTIVE";
		}

		List<UserEntity> user = userRepository.findByStatusIgnoreCase(status, pageable);
		List<UserDto> dtos = new ArrayList<>();
		for (UserEntity users : user) {
			UserDto dto = userToDto(users);
			dtos.add(dto);
//				dto.add(userToDto(users));
		}
		return dtos;
	}

	@Override
	public List<UserEntity> getUsersWithLimitOffset(int pageSize, int pageNo) {
		int limit = pageSize;
		int offset = pageNo * pageSize;

		List<UserEntity> users = userRepository.getUsersWithLimitOffset(limit, offset);

		return users;
	}

}
//@Override
//public UserDto createUser(CreateUserDto createUserDtos) {
//
//	userValidation.validateCreateUser(createUserDtos);
//
//	UserEntity user = new UserEntity();
//	user.setFirstName(createUserDtos.getFirstName());
//	user.setLastName(createUserDtos.getLastName());
//	user.setEmail(createUserDtos.getEmail());
//	user.setStatus("ACTIVE");
//
//	List<UserAddressEntity> addresses = new ArrayList<>();
//	if (createUserDtos.getAddresses() != null && !createUserDtos.getAddresses().isEmpty()) {
//
//		for (UserAddressDto addressdto : createUserDtos.getAddresses()) {
//			addressValidation.validate(addressdto);
//			UserAddressEntity address = new UserAddressEntity();
//			address.setId(addressdto.getId());
//
//			address.setCity(addressdto.getCity());
//			address.setHouseNumber(addressdto.getHouseNumber());
//			address.setPincode(addressdto.getPincode());
//			address.setState(addressdto.getState());
//			address.setStreetNumber(addressdto.getStreetNumber());
//
//			address.setUser(user);
//
//			addresses.add(address);
//		}
//
//	}
//	user.setUserAddresses(addresses);
//
//	UserEntity savedUser = userRepository.save(user);
//// here pass the runnable thread constructor here.
//	
//	Thread myemailThread = new Thread(
//			new LoginEmailtask(emailService, savedUser.getEmail(), savedUser.getFirstName()));
//	myemailThread.start();
//
//	return userToDto(savedUser);
//
//}
