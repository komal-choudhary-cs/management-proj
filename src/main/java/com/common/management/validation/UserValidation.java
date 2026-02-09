package com.common.management.validation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.management.dto.CreateUserDto;
import com.common.management.repository.UserRepository;

@Component
public class UserValidation {
	
	@Autowired
	 private UserRepository userRepository ;
	
	public void validateCreateUser(CreateUserDto createUserDtos) {

		
	    if (createUserDtos == null) {
	        throw new IllegalArgumentException("User data cannot be null");
	    }

	    if (createUserDtos.getFirstName() == null || createUserDtos.getFirstName().trim().isEmpty()) {
	        throw new IllegalArgumentException("User name is required");
	    }
	    if (createUserDtos.getLastName() == null || createUserDtos.getLastName().trim().isEmpty()) {
	        throw new IllegalArgumentException("User name is required");
	    }

	    if (createUserDtos.getEmail() == null || createUserDtos.getEmail().trim().isEmpty()) {
	        throw new IllegalArgumentException("Email is required");
	    }

	    if (!createUserDtos.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
	        throw new IllegalArgumentException("Invalid email format");
	    }
	    
	    if (userRepository.findByEmail(createUserDtos.getEmail()).isPresent()) {
            throw new IllegalArgumentException(
                    "Email already exists: " + createUserDtos.getEmail()
            );
        }
	}

	public void validateCreateUser(List<CreateUserDto> createUserDtos) {
		// TODO Auto-generated method stub
		
	}
}
	
