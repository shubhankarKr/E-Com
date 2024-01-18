package eCom.backEnd.dao;

import java.util.List;

import eCom.backEnd.model.dto.CategoryDTO;

public interface CategoryDao {
	public List<CategoryDTO> getAllCategories() throws Exception;
}
