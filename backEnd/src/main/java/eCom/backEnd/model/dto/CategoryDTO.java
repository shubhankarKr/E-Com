package eCom.backEnd.model.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


import eCom.backEnd.entity.Category;
import eCom.backEnd.entity.Products;

public class CategoryDTO {
	
	private Integer id;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private String name;

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

	public Set<ProductsDTO> getProductList() {
		return productList;
	}

	public void setProductList(Set<ProductsDTO> productList) {
		this.productList = productList;
	}
	
	public Category getCategoryEntity(CategoryDTO categoryDTO) {
		Category category = new Category();
		if (categoryDTO != null) {
			category.setName(categoryDTO.getName());
			category.setCreatedAt(categoryDTO.getCreatedAt());
			category.setUpdatedAt(categoryDTO.getUpdatedAt());
			
			Set<Products> products = new HashSet<>();
			Set<ProductsDTO> productsDTOs = categoryDTO.getProductList();
			if (productsDTOs != null) {
				for (ProductsDTO productsDTO : productsDTOs) {
					Products product= new Products();
					product.setDescription(productsDTO.getDescription());
					product.setDiscount(productsDTO.getDiscount());
					product.setImageId(productsDTO.getImageId());
					product.setName(productsDTO.getName());
					product.setBuyer(productsDTO.getBuyer());
					product.setPrice(productsDTO.getPrice());
					product.setStock(productsDTO.getStock());
					product.setId(productsDTO.getId());
					product.setCreatedAt(productsDTO.getCreatedAt());
					product.setUpdatedAt(productsDTO.getUpdatedAt());
					products.add(product);
				}
			}
			category.setProductList(products);
		}
		return category;
	}
	
	
	
}
