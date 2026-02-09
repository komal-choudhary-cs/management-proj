package com.common.management.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.common.management.Service.OrderService;
import com.common.management.dto.OrderItemResponseDto;
import com.common.management.dto.OrderRequestDto;
import com.common.management.dto.OrderResponseDto;
import com.common.management.dto.OrderitemRequestDto;
import com.common.management.entity.OrderEntity;
import com.common.management.entity.OrderItemEntity;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
  
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }



    @PostMapping
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto request) {

        OrderEntity order = orderService.createOrder(request);
        System.out.println("ORDER ID = " + order.getId());

        List<OrderItemResponseDto> itemDtos = new ArrayList<>();

        for (OrderItemEntity item : order.getItems()) {
            OrderItemResponseDto dto = new OrderItemResponseDto(
                    item.getFood().getName(),
                    item.getQuantity(),
                    item.getPrice()
            );
            itemDtos.add(dto);
        }

        return new OrderResponseDto(
                order.getId(),
                order.getTotalAmount(),
                itemDtos
        );

    }


   //  Get all orders
    @GetMapping("/getAll")
    public List<OrderEntity> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrderEntity getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }


    @GetMapping("/{id}/items")
    public List<OrderItemEntity> getOrderItems(@PathVariable Long id) {
        return orderService.getOrderItems(id);
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted with ID: " + id);
    }
}

//@PostMapping
//public OrderResponseDto createOrder(@RequestBody OrderRequestDto request) {
//  OrderEntity order = orderService.createOrder(request);
//  return new OrderResponseDto(order.getId(), order.getTotalAmount(),  order.getItems);
//}
