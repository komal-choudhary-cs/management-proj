package com.common.management.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateUserDto {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private List<UserAddressDto> addresses;

}
