package eCom.backEnd.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import eCom.backEnd.entity.Products;

public interface ProductRepository extends CrudRepository<Products, Integer> {
	public List<Products> findProductsByCategoryId(int categoryId);

	public Products findProductsByProductId(int productId);

}
