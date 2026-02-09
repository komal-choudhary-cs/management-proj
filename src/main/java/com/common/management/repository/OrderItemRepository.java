package com.common.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.common.management.entity.OrderItemEntity;

public interface OrderItemRepository  extends JpaRepository<OrderItemEntity ,Long>{

	List<OrderItemEntity> findByOrderId(Long orderId);

}
