package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping		//GET all the categories
	public Page<Category> getAllCategories(@RequestParam(defaultValue = "0") int page){
		return categoryService.findAll(PageRequest.of(page, 10));
	}
	
	@PostMapping	//create a new category
	 public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        // Perform additional validation or pre-processing if needed
        if (category.getName() == null || category.getName().isEmpty()) {
            return ResponseEntity.badRequest().build(); // Return 400 Bad Request if the name is invalid
        }
        Category savedCategory = categoryService.save(category);
        return ResponseEntity.status(201).body(savedCategory); // Return the created category with 201 status
    }

	@GetMapping("/{id}")	//GET category by Id
	public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
	    return categoryService.findById(id)
	    		.map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")	//update category by id
	 public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        if (!categoryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build(); // Return 404 if category doesn't exist
        }
        Category updatedCategory = categoryService.update(id, category);
        return ResponseEntity.ok(updatedCategory); // Return the updated category
    }
	
	@DeleteMapping("/{id}")	//Delete category by id
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
	    categoryService.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
	
}
