package com.common.management.entity;

import java.util.List;

import com.common.management.dto.UserAddressDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "User")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "status")
	private String status;

//	@OneToMany(mappedBy = "user" , cascade = CascadeType.All) // without it addresee exist in memory only
//	private List<UserAddressEntity> userAddresses;

}
