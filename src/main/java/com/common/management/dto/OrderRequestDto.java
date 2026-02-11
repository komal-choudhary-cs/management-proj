package com.common.management.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequestDto {

	private List<OrderitemRequestDto> items;
}
