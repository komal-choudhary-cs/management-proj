package com.common.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.common.management.entity.OrderEntity;
import com.common.management.entity.OrderItemEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long >{

//	void delete(OrderItemEntity order);
//	void delete(Long id);

	

}
