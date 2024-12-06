package com.example.demo.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repo.CategoryRepository;



@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Page<Category> findAll(Pageable pageable){
		return categoryRepository.findAll(pageable);
	}
	
	@Override
	public Optional<Category> findById(Long id){
		return categoryRepository.findById(id);
	}

	@Override
	public Category save(Category category) {
		// TODO Auto-generated method stub
		 if (category.getName() == null || category.getName().isEmpty()) {
			 throw new IllegalArgumentException("Category name cannot be empty");
		 }
		
		return categoryRepository.save(category);
	}

	@Override
	public Category update(Long id, Category category) {
	    Optional<Category> existingCategory = categoryRepository.findById(id);
	    if (existingCategory.isPresent()) {
	        category.setCid(id); // ensuring the ID remains consistent
	        return categoryRepository.save(category);
	    } else {
	        // Handle the case where the category doesn't exist (optional)
	        throw new RuntimeException("Category not found with id " + id);
	    }
	}
	public Category addProduct(Product prd,int id) {
		Category catagory=categoryRepository.findByCid(id);
		 List<Product> prdList =catagory.getProducts();
		 prdList.add(prd);
		 return categoryRepository.save(catagory);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		categoryRepository.deleteById(id);
	}
	
}
