package eCom.backEnd.daoImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eCom.backEnd.dao.ProductsDao;
import eCom.backEnd.dao.repository.CategoryRepository;
import eCom.backEnd.dao.repository.ColorRepository;
import eCom.backEnd.dao.repository.ProductRepository;
import eCom.backEnd.entity.Category;
import eCom.backEnd.entity.Color;
import eCom.backEnd.entity.Products;
import eCom.backEnd.model.dto.CategoryDTO;
import eCom.backEnd.model.dto.ColorDTO;
import eCom.backEnd.model.dto.ProductsDTO;
import eCom.backEnd.service.UserAuthenticationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

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
	
	@Autowired
	ColorRepository colorRepository;
	
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
		if(productsDTO.getColorList()!=null) {
			List<Color> colorList= new ArrayList<>();
			for (ColorDTO colorDTO : productsDTO.getColorList()) {
				Color color=modelMapper.map(colorDTO, Color.class);
				color.setProductId(entity.getId());
				colorRepository.save(color);
				colorList.add(color);
			}
//			entity.setColorList(colorList);
		}
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

	@SuppressWarnings("unchecked")
	@Override
	public Set<ProductsDTO> getAllProducts() throws Exception {
		Set<ProductsDTO> res= new HashSet<>();
		Query query= entityManager.createQuery("select p from Products p");
		List<Products> products= query.getResultList();
		if(!products.isEmpty()) {
			for (Products products2 : products) {
				res.add(products2.getProductsDTO(products2));
			}
		}
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductsDTO> findProductsByCategoryName(String name, Integer id) throws Exception {
		Query query = entityManager
				.createQuery("select p.productId from ProductsCategoryMapping p where p.categoryId = :id ");
		query.setParameter("id", id);
		List<Integer> productIdList = query.getResultList();

		query = entityManager.createQuery("select p from Products p where p.id in :productIdList ");
		query.setParameter("productIdList", productIdList);

		List<Products> entity = query.getResultList();
		List<ProductsDTO> output = new ArrayList<>();
		for (Products products : entity) {
			output.add(products.getProductsDTO(products));
		}
		return output;
	}
}
