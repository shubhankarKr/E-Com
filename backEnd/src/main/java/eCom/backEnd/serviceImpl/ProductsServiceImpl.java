package eCom.backEnd.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eCom.backEnd.dao.ProductsDao;
import eCom.backEnd.model.dto.ProductsDTO;
import eCom.backEnd.service.ProductsService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductsServiceImpl implements ProductsService {
	
	@Autowired
	ProductsDao productsDao;
	

	@Override
	public ProductsDTO saveProduct(ProductsDTO productsDTO) throws Exception {
		return productsDao.saveProduct(productsDTO);
	}

	@Override
	public List<ProductsDTO> saveProducts(List<ProductsDTO> productsDTOList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductsDTO updateProducts(ProductsDTO productsDTO) throws Exception {
		// TODO Auto-generated method stub
		return productsDao.updateProducts(productsDTO);
	}

	@Override
	public ProductsDTO findProducts(int productId) throws Exception {
		// TODO Auto-generated method stub
		return productsDao.findProducts(productId);
	}

	@Override
	public Set<ProductsDTO> getAllProducts() throws Exception {
		// TODO Auto-generated method stub
		return productsDao.getAllProducts();
	}
	

}
