package com.common.management.Service;

import java.util.List;

import com.common.management.dto.OrderRequestDto;
import com.common.management.dto.OrderResponseDto;
import com.common.management.dto.OrderitemRequestDto;
import com.common.management.entity.OrderEntity;
import com.common.management.entity.OrderItemEntity;

public interface OrderService {
	 OrderEntity createOrder(OrderRequestDto items);

	    List<OrderEntity> getAllOrders();

	    OrderEntity getOrderById(Long id);

	    void deleteOrder(Long id);

	    List<OrderItemEntity> getOrderItems(Long orderId);}
