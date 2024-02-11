package eCom.backEnd.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eCom.backEnd.dao.repository.CategoryRepository;
import eCom.backEnd.dao.repository.ProductRepository;
import eCom.backEnd.entity.Category;
import eCom.backEnd.entity.Products;
import eCom.backEnd.service.UserAuthenticationService;

@RestController
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	UserAuthenticationService userAuthenticationService;

	@GetMapping("/find/id/{id}")
	public Products findProductsById(@PathVariable int id) throws Exception {
		Products entity = productRepository.findByIdAndActive(id, 1);
		if (entity != null) {
			return entity;
		}
		throw new Exception("product not found with the given id");
	}

	@GetMapping("/findAll")
	public List<Products> findAllProducts() throws Exception {
		return productRepository.findAllActive();
	}

	@GetMapping("/findAll/Ignored")
	public List<Products> findAllProductsIgnored() throws Exception {
		return productRepository.findAll();
	}

	@PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
	public Products createProduct(@RequestBody Products product) throws Exception {
		if (product.getCategoryList() != null) {
			ArrayList<Integer> idList = new ArrayList<>();
			for (Category category : product.getCategoryList()) {
				idList.add(category.getId());
			}
			List<Category> categories = categoryRepository.findAllById(idList);
			if (product.getCategoryList().size() != categories.size()) {
				throw new Exception(
						"there is somethign wrong with Category data hints! please check if ids are missing");
			}
			product.setCategoryList(new HashSet<>(categories));
		}
		return productRepository.save(product);
	}

	@PostMapping("/createAll")
	public List<Products> createProductsAll(@RequestBody List<Products> products) throws Exception {
		List<Products> result = new ArrayList<>();
		return result;
	}

	@PutMapping("/update")
	public Products updateProduct(@RequestBody Products products) throws Exception {
		Products entity = findProductsById(products.getId());
		if (entity != null) {
			entity.setDescription(products.getDescription());
			entity.setDiscount(products.getDiscount());
			entity.setName(products.getName());
			entity.setPrice(products.getPrice());
		}
		return entity;
	}

	@DeleteMapping("/delete/id/{id}")
	public Boolean deleteProducts(@PathVariable int id) throws Exception {
		Products entity = productRepository.findByIdAndActive(id, 1);
		if (entity != null) {
			productRepository.deleteProduct(id);
			return true;
		}
		throw new Exception("product does not exist with given id");
	}

	@GetMapping("/allProductsByCategoryId/{categoryId}")
	public Set<Products> findAllProductsByICategoryd(@PathVariable int categoryId) {
		List<Products> productIdList = productRepository.findProductIdListByCategoryId(categoryId);
		return new HashSet<>(productIdList);
	}
}
