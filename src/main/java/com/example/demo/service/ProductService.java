package com.example.demo.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.dto.ProductAddRequest;
import com.example.demo.dto.ProductEditRequest;

public interface ProductService {

	Map<String, Object> getAllProducts();

	Map<String, Object> addProduct(ProductAddRequest request, String username);

	Map<String, Object> editProductEntity(ProductEditRequest request, UserDetails userDetails, Integer id);


}
