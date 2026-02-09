package com.common.management.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.management.dto.UserAddressDto;
import com.common.management.entity.UserAddressEntity;
import com.common.management.entity.UserEntity;
import com.common.management.exceptions.OrderNotFoundException;
import com.common.management.repository.AddressRepository;
import com.common.management.repository.UserRepository;


@Service
public class UserAddressServiceImpl implements AddressService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public UserAddressDto addressToDto(UserAddressEntity userAddress) {
		UserAddressDto adressDto = new UserAddressDto();
		adressDto.setCity(userAddress.getCity());
		adressDto.setId(userAddress.getId());
		adressDto.setHouseNumber(userAddress.getHouseNumber());
		adressDto.setPincode(userAddress.getPincode());
		adressDto.setState(userAddress.getState());
		adressDto.setStreetNumber(userAddress.getStreetNumber());
		return adressDto;

	}

	public UserAddressDto addAddress(Long userId, UserAddressDto dto) {

		UserEntity user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("userid is not valid"));
		
		UserAddressEntity address = new UserAddressEntity();
		address.setCity(dto.getCity());
		address.setHouseNumber(dto.getHouseNumber());
		address.setPincode(dto.getPincode());
		address.setState(dto.getState());
		address.setStreetNumber(dto.getStreetNumber());
		address.setUser(user);

		return addressToDto(addressRepository.save(address));

	}
	@Override
	public UserAddressDto getAddressById(Long id) {

		UserAddressEntity address = addressRepository.findById(id)
				.orElseThrow(() -> new OrderNotFoundException("Invalid address id"));

		return addressToDto(address);
	}

}
