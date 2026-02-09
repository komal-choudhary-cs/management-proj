package com.common.management.dto;

import java.util.List;



import lombok.Getter;
import lombok.Setter;
//@Setter
//@Getter
public class OrderRequestDto {
	 public List<OrderitemRequestDto> getItems() {
		return items;
	}

	public void setItems(List<OrderitemRequestDto> items) {
		this.items = items;
	}

	 private List<OrderitemRequestDto> items;
}
