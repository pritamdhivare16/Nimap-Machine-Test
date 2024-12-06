package com.example.demo.service;

import java.util.Optional;

import org.springframework.data.domain.*;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

public interface CategoryService {
	
	Page<Category> findAll(Pageable pageable);
	Optional<Category> findById(Long id);
	Category save(Category category);
	Category update(Long id, Category category);
	void deleteById(Long id);
	Category addProduct(Product product,int id);
	
}
