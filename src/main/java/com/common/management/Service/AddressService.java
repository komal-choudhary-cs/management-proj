package com.common.management.Service;

import org.springframework.stereotype.Service;

import com.common.management.dto.UserAddressDto;
import com.common.management.entity.UserAddressEntity;


public interface AddressService {
	UserAddressDto addressToDto(UserAddressEntity  userAddress);
	UserAddressDto getAddressById(Long id) ;
}
