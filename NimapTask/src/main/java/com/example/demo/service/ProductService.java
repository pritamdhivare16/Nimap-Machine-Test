package com.example.demo.service;

import java.util.Optional;

import org.springframework.data.domain.*;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

public interface ProductService {
	 	Page<Product> findAll(Pageable pageable);
	    Optional<Product> findById(Long id);
	    Product save(Product product);
	    Product update(Long id, Product product);
	    void deleteById(Long id);
	    Category CategoryByProductId(Long id);
}
