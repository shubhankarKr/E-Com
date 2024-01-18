package eCom.backEnd.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import eCom.backEnd.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Integer> {

	public Products findProductsById(int productId);

}
