package eCom.backEnd.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eCom.backEnd.dao.CategoryDao;
import eCom.backEnd.entity.Category;
import eCom.backEnd.model.dto.CategoryDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class CategoryDaoImpl implements CategoryDao{
	
	@Autowired
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryDTO> getAllCategories() throws Exception {
		Query query= entityManager.createQuery("select c from Category c");
		List<Category> categories=query.getResultList();
		List<CategoryDTO> categoryDTOs= new ArrayList<>();
		if(!categories.isEmpty()) {
			for (Category category : categories) {
				categoryDTOs.add(category.getCategoryDTO(category));
			}
		}
		return categoryDTOs;
	}

}
