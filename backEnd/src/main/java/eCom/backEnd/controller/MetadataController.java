package eCom.backEnd.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eCom.backEnd.dao.repository.CategoryRepository;
import eCom.backEnd.entity.Category;
import eCom.backEnd.model.dto.CategoryDTO;
import eCom.backEnd.service.CategoryService;

@RestController
@RequestMapping("/ecom/metadata")
public class MetadataController {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("/getAllCategory")
	public List<CategoryDTO> getAllCategory() throws Exception {
		return categoryService.getAllCategories();
	}

	@DeleteMapping("/deleteCategoryById/{id}")
	public ResponseEntity<HashMap<String, String>> deleteById(@PathVariable int id) {
		categoryRepository.deleteById(id);
		HashMap<String, String> output= new HashMap<>();
		output.put("success", "category with " + id + " has been deleted successfully");
		return new ResponseEntity<HashMap<String, String>>(output, HttpStatus.OK);

	}

	@PostMapping("/addCategory")
	public ResponseEntity<HashMap<String, String>> addCategory(@RequestBody Category category) {
		Category saved = categoryRepository.save(category);
		HashMap<String, String> output= new HashMap<>();
		output.put("success", "category with " + saved.getId() + " has been added successfully");
		return new ResponseEntity<HashMap<String, String>>(output, HttpStatus.OK);

	}

	@PostMapping("/addCategoryAll")
	public List<Category> addCategoryAll(@RequestBody List<Category> categories) {
		return (List<Category>) categoryRepository.saveAll(categories);
	}

}
