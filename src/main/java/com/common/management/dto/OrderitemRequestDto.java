package com.common.management.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderitemRequestDto {
	private Long foodId;
	private Integer quantity;
}