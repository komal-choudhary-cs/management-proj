package com.common.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.common.management.Service.AddressService;
import com.common.management.dto.UserAddressDto;

@RestController
@RequestMapping("/address")
public class UserAddressController {
	@Autowired
	private AddressService service;

	

	@GetMapping("/{id}")
	public ResponseEntity<UserAddressDto> getAddressById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getAddressById(id));
	}
}
