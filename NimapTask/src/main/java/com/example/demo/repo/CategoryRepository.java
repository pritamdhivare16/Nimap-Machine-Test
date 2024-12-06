package com.example.demo.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	Category findByCid(int cid);
	@Query("select c from Category c")
	List<Category> getAll();
}
