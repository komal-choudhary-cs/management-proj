package com.common.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddressDto {

	private long id;
	private Integer pincode;
	private Integer streetNumber;
	private String city;
	private String state;
	private Integer houseNumber;

}
