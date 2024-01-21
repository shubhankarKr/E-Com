package eCom.backEnd.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import eCom.backEnd.entity.Products;
import eCom.backEnd.model.dto.ProductsDTO;

public interface ProductRepository extends JpaRepository<Products, Integer> {

	public Products findProductsById(int productId);
//	public Products findProductsByCategoryName(String name);

}
