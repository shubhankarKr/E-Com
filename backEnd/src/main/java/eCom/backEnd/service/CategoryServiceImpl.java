package eCom.backEnd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eCom.backEnd.dao.CategoryDao;
import eCom.backEnd.model.dto.CategoryMetadataDTO;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<CategoryMetadataDTO> findAllCategoriesMD() {
		return categoryDao.findAllCategoriesMD();
	}

}
