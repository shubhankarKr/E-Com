package eCom.backEnd.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eCom.backEnd.dao.ProductsDao;
import eCom.backEnd.dao.repository.CategoryRepository;
import eCom.backEnd.entity.Category;
import eCom.backEnd.model.dto.ProductsDTO;
import eCom.backEnd.service.ProductsService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {
	
	@Autowired
	ProductsDao productsDao;
	
	@Autowired
	CategoryRepository categoryRepository;
	

	@Override
	public ProductsDTO saveProduct(ProductsDTO productsDTO) throws Exception {
		return productsDao.saveProduct(productsDTO);
	}

	@Override
	public List<ProductsDTO> saveProducts(List<ProductsDTO> productsDTOList) {
		return null;
	}

	@Override
	public ProductsDTO updateProducts(ProductsDTO productsDTO) throws Exception {
		return productsDao.updateProducts(productsDTO);
	}

	@Override
	public ProductsDTO findProducts(int productId) throws Exception {
		return productsDao.findProducts(productId);
	}

	@Override
	public Set<ProductsDTO> getAllProducts() throws Exception {
		return productsDao.getAllProducts();
	}

	@Override
	public List<ProductsDTO> findProductsByCategoryName(String name) throws Exception {
		Category category = categoryRepository.findByName(name);
		if (category == null) {
			throw new Exception(name + " category does not exists");
		}
		category.getId();
		return productsDao.findProductsByCategoryName(name, category.getId());
	}

}
