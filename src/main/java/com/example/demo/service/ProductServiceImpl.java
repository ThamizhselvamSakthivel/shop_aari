package com.example.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.Repos.AuthRepository;
import com.example.demo.Repos.ProductRepository;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductEditRequest;
import com.example.demo.dto.ProductAddRequest;
import com.example.demo.entity.Products;
import com.example.demo.entity.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private AuthRepository authRepo;

	@Override
	public Map<String, Object> getAllProducts() {

		Map<String, Object> object = new HashMap<String, Object>();

		List<ProductDto> products = productRepo.findAll().stream().map(
				t -> new ProductDto(t.getRecId(), t.getProductName(), t.getUpdatedBy(), t.getUpdatedOn(), t.getPrice()))

				.collect(Collectors.toList());

		object.put("sucess", true);
		object.put("message", "products are retrieved sucessfully");
		object.put("products", products);

		return object;
	}

	@Override
	public Map<String, Object> addProduct(ProductAddRequest request, String username) {

		Map<String, Object> object = new HashMap<String, Object>();

		List<Products> products = productRepo.findByproductName(request.productName());

		if (!products.isEmpty()) {
			object.put("sucess", false);
			object.put("message", "product is already present please try wait new one");
			return object;
		}

		List<User> user = authRepo.findByusername(username);

		if (user.isEmpty() || !user.get(0).getRole().equals("ADMIN")) {
			object.put("sucess", false);
			object.put("message", "Unauthorized user to access this");
			return object;
		}

		Products newProduct = new Products();
		newProduct.setProductName(request.productName());
		newProduct.setUpdatedBy(user.get(0).getUserId());
		newProduct.setUpdatedOn(new Date());
		newProduct.setPrice(request.price());

		Integer recId = this.productRepo.save(newProduct).getRecId();

		if (Objects.isNull(recId)) {
			object.put("sucess", false);
			object.put("message", "Unable to save the product. please once again");
			return object;
		}

		object.put("sucess", true);
		object.put("message", "product is saved successfully");

		return object;
	}

	@Override
	public Map<String, Object> editProductEntity(ProductEditRequest request, UserDetails userDetails, Integer id) {
		Map<String, Object> object = new HashMap<String, Object>();

		Products product = this.productRepo.findById(id).get();

		if (Objects.isNull(product)) {
			object.put("sucess", false);
			object.put("message", "Product is not present");
			return object;
		}

		List<User> user = authRepo.findByusername(userDetails.getUsername());

		if (user.isEmpty() || !user.get(0).getRole().equals("ADMIN")) {
			object.put("sucess", false);
			object.put("message", "Unauthorized user to access this");
			return object;
		}

		product.setPrice(request.Price());
		product.setProductName(request.productName());
		product.setUpdatedOn(new Date());
		product.setUpdatedBy(user.get(0).getUserId());

		Integer recId = this.productRepo.save(product).getRecId();

		if (Objects.isNull(recId)) {
			object.put("sucess", false);
			object.put("message", "Unable to save the product. please once again");
			return object;
		}

		object.put("sucess", true);
		object.put("message", "product is edited successfully");

		return object;

	}

}
