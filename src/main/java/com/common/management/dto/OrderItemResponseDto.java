package com.common.management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderItemResponseDto {

	private String foodName;
	private Integer quantity;
	private Double price;

	public OrderItemResponseDto(String foodName, Integer quantity, Double price) {
		this.foodName = foodName;
		this.quantity = quantity;
		this.price = price;
	}

	public String getFoodName() {
		return foodName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getPrice() {
		return price;
	}
}
