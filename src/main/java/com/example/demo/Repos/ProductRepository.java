package com.example.demo.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Products;

@Repository
@EnableJpaRepositories
public interface ProductRepository extends JpaRepository<Products, Integer>{

    List<Products> findByproductName(String productName);

}
