package eCom.backEnd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eCom.backEnd.dao.repository.AuthoriryRepository;
import eCom.backEnd.dao.repository.metadata.APIDetailsRepository;
import eCom.backEnd.entity.Authority;
import eCom.backEnd.entity.metadata.APIDetails;
import eCom.backEnd.model.dto.CategoryMetadataDTO;
import eCom.backEnd.service.CategoryService;

@RestController
@RequestMapping("/metadata")
public class MetadataController {

	@Autowired
	AuthoriryRepository authoriryRepository;

	@Autowired
	APIDetailsRepository apiDetailsRepository;

	@Autowired
	CategoryService categoryService;

	@GetMapping("/getAuthorities")
	public List<Authority> getAuthorities() throws Exception {
		return authoriryRepository.findAllByMd(1);
	}

	@GetMapping("/findAllCategoriesMD")
	public List<CategoryMetadataDTO> findAllCategoriesMD() throws Exception {
		return categoryService.findAllCategoriesMD();
	}

	@PostMapping("/apiDetails/create")
	public APIDetails createAPIDetails(@RequestBody APIDetails apiDetails) {
		apiDetails.setApi(apiDetails.getHost() + apiDetails.getApiPath());
		return apiDetailsRepository.save(apiDetails);
	}

	@GetMapping("/apiDetails/findAll")
	public List<APIDetails> findAllAPIDetails() {
		return apiDetailsRepository.findAll();
	}

	@DeleteMapping("/apiDetails/delete/id/{id}")
	public boolean deleteAPIDetails(@PathVariable int id) throws Exception {
		Optional<APIDetails> entity = apiDetailsRepository.findById(id);
		if (entity.isEmpty()) {
			throw new Exception("not found with the given id");
		}
		apiDetailsRepository.deleteById(id);
		return true;
	}

	@PutMapping("/apiDetails/update")
	public boolean updateAPIDetails(@RequestBody APIDetails apiDetails) throws Exception {
		Optional<APIDetails> entity = apiDetailsRepository.findById(apiDetails.getId());
		if (entity.isEmpty()) {
			throw new Exception("not found with the given id");
		}
		APIDetails savedEntity = entity.get();
		savedEntity.setApi(apiDetails.getHost() + apiDetails.getApiPath());
		savedEntity.setDescription(apiDetails.getDescription());
		savedEntity.setMethod(apiDetails.getMethod());
		savedEntity.setApiPath(apiDetails.getApiPath());
		savedEntity.setHost(apiDetails.getHost());
		apiDetailsRepository.save(savedEntity);
		return true;
	}
}
