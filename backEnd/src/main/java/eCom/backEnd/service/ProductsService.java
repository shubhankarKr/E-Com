package eCom.backEnd.service;

import java.util.List;


import eCom.backEnd.model.dto.ProductsDTO;


public interface ProductsService {
	public ProductsDTO saveProduct(ProductsDTO productsDTO) throws Exception;
	public List<ProductsDTO> saveProducts(List<ProductsDTO> productsDTOList);
	public ProductsDTO updateProducts(ProductsDTO productsDTO) throws Exception;
	public ProductsDTO findProducts(int productId) throws Exception;
}
