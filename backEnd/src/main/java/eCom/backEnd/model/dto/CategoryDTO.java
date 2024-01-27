package eCom.backEnd.model.dto;

import java.time.LocalDateTime;
import java.util.Set;

import eCom.backEnd.entity.Products;

public class CategoryDTO {

	private Integer id;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;

	private String name;

	private int active;

	Set<Products> productList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Set<Products> getProductList() {
		return productList;
	}

	public void setProductList(Set<Products> productList) {
		this.productList = productList;
	}

}
