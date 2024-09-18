package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ProductAddRequest;
import com.example.demo.dto.ProductEditRequest;
import com.example.demo.service.ProductService;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/all")
	public ResponseEntity<Object> getAllProducts() {
		Map<String, Object> object = new HashMap<String, Object>();
		try {
			object = this.productService.getAllProducts();
		} catch (Exception e) {
			e.printStackTrace();
			object.put("success", false);
			object.put("message", String.format("error while getting product %s", e.getMessage()));
			return new ResponseEntity<Object>(object, HttpStatus.BAD_REQUEST);
		}
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Object>(object, httpHeaders, HttpStatus.OK);

	}

	@PostMapping("/add")
	public ResponseEntity<Object> addProduct(@NonNull ProductAddRequest request, HttpServletRequest httpRequest,
			HttpServletResponse httpReponse, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> object = new HashMap<String, Object>();
		try {

			if (request.productName().isBlank()) {
				object.put("success", false);
				object.put("message", String.format("product name is should not blank"));
				return new ResponseEntity<Object>(object, HttpStatus.BAD_REQUEST);
			}
			if (userDetails.getUsername().isBlank()) {
				object.put("success", false);
				object.put("message", String.format("Unauthorized User to add product"));
				return new ResponseEntity<Object>(object, HttpStatus.BAD_REQUEST);
			}

			object = this.productService.addProduct(request, userDetails.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			object.put("success", false);
			object.put("message", String.format("error while addProduct %s", e.getMessage()));
			return new ResponseEntity<Object>(object, HttpStatus.BAD_REQUEST);
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Object>(object, httpHeaders, HttpStatus.OK);

	}

	@PostMapping("/update/{id}")
	public ResponseEntity<Object> editProductEntity(
			@RequestParam Integer id,
			ProductEditRequest request, HttpServletRequest httpRequest,
			HttpServletResponse httpReponse, @AuthenticationPrincipal UserDetails userDetails) {
		Map<String, Object> object = new HashMap<String, Object>();
		try {
			if (Objects.isNull(request)) {
				object.put("success", false);
				object.put("message", String.format("Edit request is should not blank"));
				return new ResponseEntity<Object>(object, HttpStatus.BAD_REQUEST);
			}
			if(Objects.isNull(id)) {
				object.put("success", false);
				object.put("message", String.format("product id is should not blank"));
				return new ResponseEntity<Object>(object, HttpStatus.BAD_REQUEST);
			}
			object = this.productService.editProductEntity(request, userDetails,id);
		} catch (Exception e) {
			e.printStackTrace();
			object.put("success", false);
			object.put("message", String.format("error while editProductEntity %s", e.getMessage()));
			return new ResponseEntity<Object>(object, HttpStatus.BAD_REQUEST);
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<Object>(object, httpHeaders, HttpStatus.OK);

	}

}
