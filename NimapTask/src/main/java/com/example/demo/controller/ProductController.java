package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	
	  @Autowired
	  private ProductService productService;
	  
	  @Autowired
		private CategoryService categoryService;
	  
	  @GetMapping	//get all the products
	  public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page) {
		  return productService.findAll(PageRequest.of(page, 10));
	  }
	  
	  
	@PostMapping("/{id}") // this id is cid;
		private Category addproduct(@RequestBody Product product,@PathVariable int id) {
			return categoryService.addProduct(product,id);
		}

	  @GetMapping("/{id}")	//GET product by Id
	  public Category getProductById(@PathVariable Long id) {
		  
		  return productService.CategoryByProductId(id);
	               
	  }

	  @PutMapping("/{id}")	//update product by id
	  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		  return ResponseEntity.ok(productService.update(id, product));
	  }
	  
	  @DeleteMapping("/{id}")	//Delete product by id
	  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		  productService.deleteById(id);
		  return ResponseEntity.noContent().build();
	    }
	  
}
