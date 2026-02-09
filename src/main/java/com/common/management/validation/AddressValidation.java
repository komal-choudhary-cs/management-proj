package com.common.management.validation;

import org.springframework.stereotype.Component;

import com.common.management.dto.UserAddressDto;
import com.common.management.exceptions.MethodTypeMismatchException;
@Component
public class AddressValidation {
  public void validate(UserAddressDto useraddressDto) {
	  
	  if(useraddressDto.getCity()==null|| useraddressDto.getCity().isEmpty()) {
		  throw new MethodTypeMismatchException(" city is required");
		  
	  }
	  if(useraddressDto.getHouseNumber()==null|| useraddressDto.getHouseNumber()<=0) {
		  throw new MethodTypeMismatchException(" houseNumber is required"); 
	  }
	  
	if(useraddressDto.getState()==null|| useraddressDto.getState().isEmpty()) {
		  throw new MethodTypeMismatchException(" state is required"); 

	}
	if(useraddressDto.getPincode()==null ||  useraddressDto.getPincode()<=0) {
		  throw new MethodTypeMismatchException(" pincode is required"); 
	}
	if(useraddressDto.getStreetNumber()==null|| useraddressDto.getStreetNumber()<=0) {
		  throw new MethodTypeMismatchException(" streetNumber is required"); 

	}
  }
}
