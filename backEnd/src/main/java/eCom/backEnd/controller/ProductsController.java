package eCom.backEnd.controller;

import java.util.List;

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
import eCom.backEnd.service.UserAuthenticationService;

@RestController
@RequestMapping("ecom/products")
public class ProductsController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	UserAuthenticationService authenticationService;

	@GetMapping("/getByCategoryId/{categoryId}")
	public List<Products> findProductsByCategoryId(@PathVariable int categoryId) {
		return productRepository.findProductsByCategoryId(categoryId);
	}

	@GetMapping("/getByProductId/{productId}")
	public Products findProductsByProductId(@PathVariable int productId) {
		return productRepository.findProductsByProductId(productId);
	}

	@PostMapping("/save")
	public Products saveProduct(@RequestBody Products products) {
		products.setUpdatedBy(authenticationService.getCurrentUser());
		return productRepository.save(products);
	}

	@PostMapping("/saveAll")
	public List<Products> saveProduct(@RequestBody List<Products> products) {
		return (List<Products>) productRepository.saveAll(products);
	}

	@PutMapping("/update")
	public Products updateProduct(@RequestBody Products products) {
		return productRepository.save(products);
	}
}
