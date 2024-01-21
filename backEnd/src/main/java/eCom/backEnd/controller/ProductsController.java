package eCom.backEnd.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eCom.backEnd.dao.repository.ProductRepository;
import eCom.backEnd.entity.Products;
import eCom.backEnd.model.dto.ProductsDTO;
import eCom.backEnd.service.ProductsService;

@RestController
@RequestMapping("/ecom/products")
public class ProductsController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductsService productsService;

	@GetMapping("/getByProductId/{productId}")
	public ProductsDTO findProductsById(@PathVariable int productId) throws Exception {
		return productsService.findProducts(productId);
	}
	
	@GetMapping("/getByCategoryName/{name}")
	public List<ProductsDTO> findProductsByCategoryName(@PathVariable String name) throws Exception {
		return productsService.findProductsByCategoryName(name);
	}
	
	@GetMapping("/getAllProducts")
	public Set<ProductsDTO> getAllProducts() throws Exception {
		return productsService.getAllProducts();
	}

	@PostMapping(value = "/save" , consumes="application/json",produces = "application/json")
	public ProductsDTO saveProduct(@RequestBody ProductsDTO productsDTO) throws Exception {
		return productsService.saveProduct(productsDTO);
	}

	@PostMapping("/saveAll")
	public List<Products> saveProduct(@RequestBody List<Products> products) {
		return (List<Products>) productRepository.saveAll(products);
	}

	@PutMapping("/update")
	public ProductsDTO updateProduct(@RequestBody ProductsDTO productsDTO) throws Exception {
		return productsService.updateProducts(productsDTO);
	}
}
