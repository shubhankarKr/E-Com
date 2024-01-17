package eCom.backEnd.entity.mapping;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Table(name = "products_category_mapping")
public class ProductsCategoryMapping {

	@Column(name = "product_id")
	private int productId;

	@Column(name = "product_id")
	private int categoryId;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

}
