package com.common.management.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "order_item_entity")
public class OrderItemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private OrderEntity order;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "food_id", nullable = false)
	private FoodMenuEntity food;

	private Integer quantity;
	private Double price;

//	public void setOrder(OrderEntity order) {
//		this.order = order;
//	}
}