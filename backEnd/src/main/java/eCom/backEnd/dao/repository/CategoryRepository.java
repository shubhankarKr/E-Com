package eCom.backEnd.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eCom.backEnd.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	public Category findCategoryById(Integer id);

	public Category findByName(String name);

	@Query("select p.categoryId from ProductsCategoryMapping p where p.productId = ?1")
	public List<Integer> findProductIdListByCategoryId(int productId);
}
