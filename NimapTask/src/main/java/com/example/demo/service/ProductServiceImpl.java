package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	


	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Page<Product> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return productRepository.findAll(pageable);
	}
	public Category CategoryByProductId(Long id) {
		
		List<Category> category=categoryRepository.getAll();
		Category Cat=new Category();
		boolean indi=false;
		for(Category cr:category) {
			List<Product> prd=cr.getProducts();
			for(Product pr:prd) {
				if(pr.getPid()==id) {
					Cat=cr;
					Cat.setProducts(null);
					List<Product> prdd=new ArrayList<>();
					prdd.add(pr);
					Cat.setProducts(prdd);
					indi=true;
					break;
					
				}
				
			}
			if(indi) {
				break;
			}
		}
		return Cat;
	}
	@Override
	public Optional<Product> findById(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id);
	}

	
	@Transactional
	@Override
	public Product save(Product product) {
	    if (product.getName() == null || product.getName().isEmpty()) {
	        throw new IllegalArgumentException("Product name cannot be empty");
	    }
	    if (product.getPrice() == null || product.getPrice() <= 0) {
	        throw new IllegalArgumentException("Product price must be greater than 0");
	    }
	/*    if (product.getCategory() == null || product.getCategory().getCid() == null) {
	        throw new IllegalArgumentException("Category cannot be null");
	    }

	    
	    // Fetch the category based on the provided cid
	    Category category = categoryRepository.findById(product.getCategory().getCid())
	            .orElseThrow(() -> new IllegalArgumentException("Category not found with id " + product.getCategory().getCid()));
	    
	    // Set the fetched category in the product
	    product.setCategory(category);*/
	    
	    return productRepository.save(product);
	}
	
	


	@Override
	@Transactional
	public Product update(Long id, Product product) {
	    Optional<Product> existingProduct = productRepository.findById(id);
	    if (existingProduct.isPresent()) {
	        product.setPid(id); // ensure the ID remains consistent
	        return productRepository.save(product);
	    } else {
	        throw new RuntimeException("Product not found with id " + id);
	    }
	}


	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
	}

}
