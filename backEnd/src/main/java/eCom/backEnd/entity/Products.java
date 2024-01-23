package eCom.backEnd.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import eCom.backEnd.model.dto.CategoryDTO;
import eCom.backEnd.model.dto.ColorDTO;
import eCom.backEnd.model.dto.ProductsDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Products {

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	private String description;

	private String name;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "price")
	private Integer price;

	private Integer discount;

	@Column(name = "image_id")
	private String imageId;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "products_category_mapping", 
		joinColumns = @JoinColumn(name = "product_id"), 
		inverseJoinColumns = @JoinColumn(name = "category_id"))
	@JsonManagedReference
	private Set<Category> categoryList;

	private Integer stock;

	private String buyer;
	
	@OneToMany(mappedBy = "productId",fetch = FetchType.EAGER)
	private List<Color> colorList;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public Set<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Set<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	
	public List<Color> getColorList() {
		return colorList;
	}

	public void setColorList(List<Color> colorList) {
		this.colorList = colorList;
	}

	public ProductsDTO getProductsDTO(Products products) {
		ProductsDTO productsDTO = new ProductsDTO();
		if (products != null) {
			productsDTO.setDescription(products.getDescription());
			productsDTO.setDiscount(products.getDiscount());
			productsDTO.setImageId(products.getImageId());
			productsDTO.setName(products.getName());
			productsDTO.setBuyer(products.getBuyer());
			productsDTO.setPrice(products.getPrice());
			productsDTO.setStock(products.getStock());
			productsDTO.setId(products.getId());
			Set<CategoryDTO> categoryDTOs = new HashSet<>();
			if (products.getCategoryList() != null) {
				for (Category category : products.getCategoryList()) {
					CategoryDTO categoryDTO = new CategoryDTO();
					categoryDTO.setCreatedAt(category.getCreatedAt());
					categoryDTO.setId(category.getId());
					categoryDTO.setName(category.getName());
					categoryDTO.setUpdatedAt(category.getUpdatedAt());
					categoryDTOs.add(categoryDTO);
				}

			}
			productsDTO.setCategoryList(categoryDTOs);
			
			List<ColorDTO> colorDTOs= new ArrayList<>();
			if(products.getColorList()!=null) {
				for (Color color : products.getColorList()) {
					ColorDTO dto= new ColorDTO();
					dto.setId(color.getId());
					dto.setColorCode(color.getColorCode());
					colorDTOs.add(dto);
				}
			}
			productsDTO.setColorList(colorDTOs);

		}
		return productsDTO;
	}

}
