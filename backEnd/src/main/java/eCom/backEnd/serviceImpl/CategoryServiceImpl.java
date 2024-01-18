package eCom.backEnd.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eCom.backEnd.dao.CategoryDao;
import eCom.backEnd.model.dto.CategoryDTO;
import eCom.backEnd.service.CategoryService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDao categoryDao;

	@Override
	public List<CategoryDTO> getAllCategories() throws Exception {
		return categoryDao.getAllCategories();
	}

}
