

package com.example.demo.entity;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;


@Entity
@Table(name = "categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cid;
	
	@Column(nullable = false)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
//	@JsonManagedReference
	private List<Product> products = new ArrayList<>();
	
	
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Category(Long cid, String name, List<Product> products) {
		super();
		this.cid = cid;
		this.name = name;
		this.products = products;
	}


	public Long getCid() {
		return cid;
	}


	public void setCid(Long cid) {
		this.cid = cid;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	

}
