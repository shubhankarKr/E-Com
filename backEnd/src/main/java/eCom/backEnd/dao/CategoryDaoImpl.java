package eCom.backEnd.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eCom.backEnd.model.dto.CategoryMetadataDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryMetadataDTO> findAllCategoriesMD() {
		Query query = entityManager.createQuery("select e.id,e.name from Category e");
		List<Object[]> entitiesList = query.getResultList();
		List<CategoryMetadataDTO> result = new ArrayList<>();
		for (int i = 0; i < entitiesList.size(); i++) {
			result.add(new CategoryMetadataDTO(entitiesList.get(i)[0], entitiesList.get(i)[1]));
		}
		return result;
	}

}
