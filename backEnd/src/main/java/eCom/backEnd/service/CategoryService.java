package eCom.backEnd.service;

import java.util.List;

import eCom.backEnd.model.dto.CategoryDTO;

public interface CategoryService {
	public List<CategoryDTO> getAllCategories() throws Exception;
}
