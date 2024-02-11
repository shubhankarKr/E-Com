package eCom.backEnd.service;

import java.util.List;

import eCom.backEnd.model.dto.CategoryMetadataDTO;

public interface CategoryService {
	public List<CategoryMetadataDTO> findAllCategoriesMD();
}
