package eCom.backEnd.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eCom.backEnd.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Integer> {
	public List<Products> findProductsByCategoryId(int categoryId);

	public Products findProductsById(int productId);

}
