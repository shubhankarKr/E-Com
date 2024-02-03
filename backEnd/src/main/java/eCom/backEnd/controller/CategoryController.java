package eCom.backEnd.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eCom.backEnd.dao.repository.CategoryRepository;
import eCom.backEnd.entity.Category;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/findById/{id}")
	public Optional<Category> findCategoryById(@PathVariable Integer id) {
		return categoryRepository.findById(id);
	}

	@GetMapping("/findAll")
	public Set<Category> findCategories() {
		List<Category> categories = categoryRepository.findAll();

		return new HashSet<>(categories);
	}

	@GetMapping("/allProductsByCategoryId/{productId}")
	public Set<Category> findAllProductsByICategoryd(@PathVariable int productId) {
		List<Integer> productIdList = categoryRepository.findProductIdListByCategoryId(productId);
		List<Category> categories = categoryRepository.findAllById(productIdList);
		return new HashSet<>(categories);
	}
}
