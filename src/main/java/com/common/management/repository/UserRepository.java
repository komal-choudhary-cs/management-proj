package com.common.management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.common.management.entity.UserEntity;

public  interface UserRepository extends JpaRepository<UserEntity,Long>{
	Optional<UserEntity> findByEmail(String email);
	
    List<UserEntity> findByStatusIgnoreCase(String status);


	List<UserEntity> findByStatusIgnoreCase(String status, Pageable pageable);
	@Query("SELECT u FROM UserEntity u")
	List<UserEntity> getUsersWithLimitOffset(int limit, int offset);
	

}
