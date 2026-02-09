package com.common.management.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.management.Service.UserService;
import com.common.management.dto.CreateUserDto;
import com.common.management.dto.UserDto;
import com.common.management.dto.UserResponseDto;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;


	

	@PostMapping("/save")
	public List<UserDto> createUsers(
	        @RequestBody List<CreateUserDto> createuserdtos) {
	    return service.createUsers(createuserdtos);
	}
	@GetMapping("/getAllUsers")
	public ResponseEntity<?> getAllUsers(@RequestParam(required = false) Long id,
			@RequestParam(required = false, defaultValue = "active") String status,
			@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "3") int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);

		if (id != null) {
			return ResponseEntity.ok(service.GetUserById(id));
		}

		List<UserDto> users = new ArrayList<>();
		if (status == null || status.equalsIgnoreCase("active")) {
			users = service.GetUserByStatus("active", pageable);

		} else if (status.equalsIgnoreCase("inactive")) {
			users = service.GetUserByStatus("inactive", pageable);

		} else {
			throw new IllegalArgumentException("user not found");
		}

		UserResponseDto response = new UserResponseDto();
		response.setUsers(users);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public UserDto GetUserbyId(@PathVariable Long id) {
		return service.GetUserById(id);

	}

	@DeleteMapping("/delete/{id}")
	public UserDto DeleteUserById(@PathVariable Long id) {
		return service.DeletUserById(id);
	}

}

//List<UserDto> users = service.GetUserByStatus(status);
//
//UserResponseDto response = new UserResponseDto();
//response.setUsers(users);
// @GetMapping("/getAllUsers")
//public List< UserDto> getAllUsers() {
//	return service. getAllUsers();
//}
