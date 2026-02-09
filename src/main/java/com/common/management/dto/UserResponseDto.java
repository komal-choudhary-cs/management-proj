package com.common.management.dto;

import java.util.List;


import lombok.Getter;
import lombok.Setter;
//@Getter
//@Setter
public class UserResponseDto {
	
	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}

	private List<UserDto> users;

}
