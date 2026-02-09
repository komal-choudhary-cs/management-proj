
package com.common.management.dto;

import java.util.List;



import lombok.Getter;
import lombok.Setter;
//
@Setter
@Getter

public class OrderResponseDto {
	

	    private Long orderId;
	    private Double totalAmount;
	    private List<OrderItemResponseDto> items;

	    public OrderResponseDto(Long orderId, Double totalAmount, List<OrderItemResponseDto> items) {
	        this.orderId = orderId;
	        this.totalAmount = totalAmount;
	        this.items = items;
	    }

	  
	}


		