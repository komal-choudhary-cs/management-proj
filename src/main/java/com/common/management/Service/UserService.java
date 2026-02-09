package com.common.management.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.common.management.dto.CreateUserDto;
import com.common.management.dto.UserDto;
import com.common.management.entity.UserEntity;
@Service
public interface UserService {
List<UserDto> getAllUsers();
UserDto GetUserById(Long id);
UserDto DeletUserById(Long id);

List<UserDto> GetUserByStatus(String status , Pageable pageable);



	List<UserEntity> getUsersWithLimitOffset(int pageNo, int pageSize);
//	UserDto createUsers(List<CreateUserDto> createuserdto);
	List<UserDto> createUsers(List<CreateUserDto> createUserDtos);
	

	

}
