package eCom.backEnd.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eCom.backEnd.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	public Category findCategoryById(Integer id);
	public Category findByName(String name);
}
