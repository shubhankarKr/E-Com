package eCom.backEnd.model.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class CategoryDTO {
	private Integer id;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private String name;

	private Short active;

	Set<ProductsDTO> productList;

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

	public Short getActive() {
		return active;
	}

	public void setActive(Short active) {
		this.active = active;
	}

	public Set<ProductsDTO> getProductList() {
		return productList;
	}

	public void setProductList(Set<ProductsDTO> productList) {
		this.productList = productList;
	}

}
