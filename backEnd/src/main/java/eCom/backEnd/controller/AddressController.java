package eCom.backEnd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eCom.backEnd.dao.repository.AddressRepository;
import eCom.backEnd.entity.Address;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/ecom/address")
public class AddressController {
	
	@Autowired
	AddressRepository addressRepository;
	
	@PostMapping("/create")
	public Address createAddress(@Valid @RequestBody Address address) {
		return addressRepository.save(address);
	}
	
}
