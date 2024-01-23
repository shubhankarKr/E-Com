package eCom.backEnd.model.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import eCom.backEnd.entity.Category;
import eCom.backEnd.entity.Products;

public class ProductsDTO {
	private Integer id;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	private String description;

	private String name;

	private String updatedBy;

	private Integer price;

	private Integer discount;

	private String imageId;

	private Set<CategoryDTO> categoryList = new HashSet<>();

	private Integer stock;

	private String buyer;
	
	private List<ColorDTO> colorList;
	
	@Autowired
	ModelMapper modelMapper;

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

	public Set<CategoryDTO> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(Set<CategoryDTO> categoryList) {
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
	
	public List<ColorDTO> getColorList() {
		return colorList;
	}

	public void setColorList(List<ColorDTO> colorList) {
		this.colorList = colorList;
	}

	public Products getProductsEntity(ProductsDTO productsDTO) {
		Products products = new Products();
		if (productsDTO != null) {
			products.setDescription(productsDTO.getDescription());
			products.setDiscount(productsDTO.getDiscount());
			products.setImageId(productsDTO.getImageId());
			products.setName(productsDTO.getName());
			products.setBuyer(productsDTO.getBuyer());
			products.setPrice(productsDTO.getPrice());
			products.setStock(productsDTO.getStock());
			Set<Category> categories = new HashSet<>();
			Set<CategoryDTO> categoryDTOs = productsDTO.getCategoryList();
			if (categoryDTOs != null) {
				for (CategoryDTO categoryDTO : categoryDTOs) {
					Category category = new Category();
					category.setName(categoryDTO.getName());
					category.setCreatedAt(categoryDTO.getCreatedAt());
					category.setId(categoryDTO.getId());
					category.setUpdatedAt(categoryDTO.getUpdatedAt());
					categories.add(category);
				}
			}
			products.setCategoryList(categories);
			
//			List<Color> colorList= new ArrayList<>();
//			if(productsDTO.getColorDTOList()!=null) {
//				for (ColorDTO dto : productsDTO.getColorDTOList()) {
//					colorList.add(modelMapper.map(dto, Color.class));
//				}
//			}
//			products.setColorList(colorList);
		}
		return products;
	}

}
