package eCom.backEnd.controller;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/ecom/products")
public class ProductsController {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	UserAuthenticationService userAuthenticationService;

	@GetMapping("/find/id/{id}")
	public Products findProductsById(@PathVariable int id) throws Exception {
		Products entity = productRepository.findByIdAndActive(id,1);
		if (entity!=null) {
			return entity;
		}
		throw new Exception("product not found with the given id");
	}

	@GetMapping("findAll")
	public List<Products> findAllProducts() throws Exception {
		return productRepository.findAllByActive(1);
	}
	
	@GetMapping("find/all/Ignored")
	public List<Products> findAllProductsIgnored() throws Exception {
		return productRepository.findAll();
	}

	@PostMapping("/create")
	public Products createProduct(@RequestBody Products product) throws Exception {
		if(product.getCategoryList()!=null) {
			ArrayList<Integer> idList=new ArrayList<>();
			for (Category category : product.getCategoryList()) {
				idList.add(category.getId());
			}
			List<Category> categories= categoryRepository.findAllById(idList);
			if(product.getCategoryList().size() != categories.size()) {
				throw new Exception("there is somethign wrong with Category data hints! please check if ids are missing");
			}
			product.setCategoryList(categories);
		}
		product.setUpdatedBy(userAuthenticationService.getCurrentUser());
		return productRepository.save(product);
	}
	
	@PostMapping("/createAll")
	public List<Products> createProductsAll(@RequestBody List<Products> products) throws Exception {
		List<Products> result=new ArrayList<>();
		if(result!=null) {
			for (Products pro : products) {
				result.add(createProduct(pro));
			}
		}
		return result;
	}

	@PutMapping("/update")
	public Products updateProduct(@RequestBody Products products) throws Exception {
		Products entity=findProductsById(products.getId());
		if(entity!=null) {
			entity.setUpdatedBy(userAuthenticationService.getCurrentUser());
			entity.setDescription(products.getDescription());
			entity.setDiscount(products.getDiscount());
			entity.setName(products.getName());
			entity.setPrice(products.getPrice());
		}
		return entity;
	}
	
	@DeleteMapping("/delete/id/{id}")
	public Boolean deleteProducts(@PathVariable int id) throws Exception {
		Products entity=productRepository.findByIdAndActive(id, 1);
		if(entity!=null) {
			productRepository.deleteProduct(id);
			return true;
		}
		throw new Exception("product does not exist with given id");
	}
}
