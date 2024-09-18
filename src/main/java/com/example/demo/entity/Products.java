package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Products {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer recId;

	@Column(name = "product_name")
	private String productName;

	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "updated_on")
	private Date updatedOn;

	@Column(name = "price")
	private Integer price;
}

