package eCom.backEnd.daoImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eCom.backEnd.dao.ProductsDao;
import eCom.backEnd.dao.repository.CategoryRepository;
import eCom.backEnd.dao.repository.ProductRepository;
import eCom.backEnd.entity.Category;
import eCom.backEnd.entity.Products;
import eCom.backEnd.model.dto.CategoryDTO;
import eCom.backEnd.model.dto.ProductsDTO;
import eCom.backEnd.service.UserAuthenticationService;
import jakarta.persistence.EntityManager;

@Repository
public class ProductsDaoImpl implements ProductsDao{
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	EntityManager entityManager;
	
	@Autowired
	UserAuthenticationService userAuthenticationService;
	
	@Override
	public ProductsDTO saveProduct(ProductsDTO productsDTO) throws Exception {
		Set<Category> categories=new HashSet<>();
		for (CategoryDTO categoryDTO : productsDTO.getCategoryList()) {
			if(categoryDTO.getId() == null) {
				throw new Exception(" category model is wrong for "+categoryDTO.getName());
			}
			Category category=categoryRepository.findCategoryById(categoryDTO.getId());
			categories.add(category);
		}
		Products entity= productsDTO.getProductsEntity(productsDTO);
		entity.setUpdatedBy(userAuthenticationService.getCurrentUser());
		entity.setCategoryList(categories);
		entityManager.persist(entity);
		ProductsDTO dto= entity.getProductsDTO(entity);
		return dto;
	}

	@Override
	public List<ProductsDTO> saveProducts(List<ProductsDTO> productsDTOList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductsDTO updateProducts(ProductsDTO productsDTO) throws Exception {
		return saveProduct(productsDTO);
	}

	@Override
	public ProductsDTO findProducts(int productId) {
		Products entity= entityManager.find(Products.class, productId);
		return entity.getProductsDTO(entity);
	}

}
