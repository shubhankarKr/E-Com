package eCom.backEnd.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import eCom.backEnd.model.dto.CategoryDTO;
import eCom.backEnd.model.dto.ProductsDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private Integer id;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	private String name;

	@ManyToMany(mappedBy = "categoryList")
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

	public Set<Products> getProductList() {
		return productList;
	}

	public void setProductList(Set<Products> productList) {
		this.productList = productList;
	}

	public CategoryDTO getCategoryDTO(Category categoryDTO) {
		CategoryDTO category = new CategoryDTO();
		if (categoryDTO != null) {
			category.setName(categoryDTO.getName());
			category.setCreatedAt(categoryDTO.getCreatedAt());
			category.setUpdatedAt(categoryDTO.getUpdatedAt());
			category.setId(categoryDTO.getId());
			Set<ProductsDTO> productsDTOs = new HashSet<>();
			Set<Products> products = categoryDTO.getProductList();
			if (products != null) {
				for (Products product : products) {
					ProductsDTO productDTo = new ProductsDTO();
					productDTo.setColor(product.getColor());
					productDTo.setDescription(product.getDescription());
					productDTo.setDiscount(product.getDiscount());
					productDTo.setImageId(product.getImageId());
					productDTo.setName(product.getName());
					productDTo.setBuyer(product.getBuyer());
					productDTo.setPrice(product.getPrice());
					productDTo.setStock(product.getStock());
					productDTo.setId(product.getId());
					productDTo.setCreatedAt(product.getCreatedAt());
					productDTo.setUpdatedAt(product.getUpdatedAt());
					productsDTOs.add(productDTo);
				}
			}
			category.setProductList(productsDTOs);
		}
		return category;

	}
}