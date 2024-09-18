package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "orders")
@Getter
@Setter
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer orderId;

	@Column(name = "order_name")
	private String OrderName;

	@Column(name = "order_time")
	private Date orderTime;

	@Column(name = "order_address")
	private String orderAddress;

	@Column(name = "order_amount")
	private String orderAmount;

}
