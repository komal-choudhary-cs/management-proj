package com.common.management.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
@Setter
@Getter
@Table(name = "orders")

public class OrderEntity {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // generated the value of primary key automatically rely to an
														// identity column in db
	private Long id;

	private Double totalAmount;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItemEntity> items = new ArrayList<>();

	public void addItem(OrderItemEntity item) {
		this.items.add(item);
		item.setOrder(this);
	}

}
