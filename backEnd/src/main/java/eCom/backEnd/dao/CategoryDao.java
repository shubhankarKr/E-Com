package eCom.backEnd.dao;

import java.util.List;

import eCom.backEnd.model.dto.CategoryMetadataDTO;

public interface CategoryDao {
	public List<CategoryMetadataDTO> findAllCategoriesMD();
}
