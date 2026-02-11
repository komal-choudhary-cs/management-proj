package com.common.management.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String status;

	private List<UserAddressDto> addresses;

}
