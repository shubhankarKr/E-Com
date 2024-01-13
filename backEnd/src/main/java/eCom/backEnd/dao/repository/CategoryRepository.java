package eCom.backEnd.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import eCom.backEnd.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
	public List<Category> findAll();

	public void deleteById(int id);
}
