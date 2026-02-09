package com.common.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.common.management.entity.UserAddressEntity;

public interface AddressRepository  extends JpaRepository<UserAddressEntity, Long> {
	
    List<UserAddressEntity> findByUserId(Long userId);
    
}
