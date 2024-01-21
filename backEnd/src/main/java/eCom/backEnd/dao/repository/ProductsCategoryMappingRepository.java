package eCom.backEnd.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eCom.backEnd.entity.ProductsCategoryMapping;

public interface ProductsCategoryMappingRepository extends JpaRepository<ProductsCategoryMapping, Integer>{
	public List<ProductsCategoryMapping> findById(int id);
}
