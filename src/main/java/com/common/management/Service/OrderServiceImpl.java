package com.common.management.Service;

import com.common.management.dto.OrderRequestDto;
import com.common.management.dto.OrderitemRequestDto;
import com.common.management.entity.*;
import com.common.management.exceptions.OrderNotFoundException;
import com.common.management.repository.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private FoodMenuRepository foodMenuRepository;

	@Transactional
	public OrderEntity createOrder(OrderRequestDto request) {

		OrderEntity order = new OrderEntity();
		double totalAmount = 0;

		for (OrderitemRequestDto itemDto : request.getItems()) {

			FoodMenuEntity food = foodMenuRepository.findById(itemDto.getFoodId())
					.orElseThrow(() -> new RuntimeException("Food not found"));
			if (food.getAvailableQuantity() < itemDto.getQuantity()) {
	            throw new RuntimeException("Insufficient quantity for: " + food.getName());
	        }
			food.setAvailableQuantity(food.getAvailableQuantity() - itemDto.getQuantity());

			OrderItemEntity orderItem = new OrderItemEntity();
			orderItem.setFood(food);
			orderItem.setQuantity(itemDto.getQuantity());
           orderItem.setOrder(order);
			orderItem.setPrice(food.getPrice());
			order.addItem(orderItem);
			

			totalAmount += food.getPrice() * itemDto.getQuantity();
	     

			
			   foodMenuRepository.save(food); 
			order.setTotalAmount(totalAmount);
		}

		

		OrderEntity savedOrder = orderRepository.save(order);
		System.out.println("SAVED ORDER ID = " + savedOrder.getId());
		 System.out.println("you are welcomed here");
		return savedOrder;

	}

	@Override
	public List<OrderEntity> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public OrderEntity getOrderById(Long id) {
		return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found: " + id));
	}

	@Override
	public   void deleteOrder(Long id) {
		  try {
	            OrderEntity order = orderRepository.findById(id)
	                    .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found"));
	            orderRepository.delete(order);
	        } catch (Exception e) {
	            throw new RuntimeException("Failed to delete order: " + e.getMessage(), e);
	        }
	}

	@Override
	public List<OrderItemEntity> getOrderItems(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

//	s
}
