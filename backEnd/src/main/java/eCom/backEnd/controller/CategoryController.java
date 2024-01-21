package eCom.backEnd.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eCom.backEnd.dao.repository.CategoryRepository;
import eCom.backEnd.entity.Category;

@RestController
@RequestMapping("ecom/category")
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("/findById/{id}")
	public Optional<Category> findCategoryById(@PathVariable Integer id) {
		return categoryRepository.findById(id);
	}
}
