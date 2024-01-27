package eCom.backEnd.dao.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import eCom.backEnd.entity.Products;

public interface ProductRepository extends JpaRepository<Products, Integer> {
	
	public Products findByIdAndActive(int id,int active);
	
	@Transactional
	@Modifying
	@Query("update Products p set p.active = 0 where p.id = ?1" )
	public int deleteProduct(int id);
	
	public List<Products> findAllByActive(int active);

}
