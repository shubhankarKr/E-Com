package eCom.backEnd.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eCom.backEnd.dao.repository.AddressRepository;
import eCom.backEnd.entity.Address;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	AddressRepository addressRepository;

	@PostMapping("/create")
	public Address createAddress(@Valid @RequestBody Address address) {
		return addressRepository.save(address);
	}

	@PutMapping("/update")
	public Address updateAddress(@Valid @RequestBody Address address) throws Exception {
		if (address.getId() == null) {
			throw new Exception("Address id is missing");
		}
		Optional<Address> entity = addressRepository.findById(address.getId());
		if (entity.isPresent()) {
			Address savedEntity = entity.get();
			savedEntity.setAddressType(address.getAddressType());
			savedEntity.setCity(address.getCity());
			savedEntity.setLandmark(address.getLandmark());
			savedEntity.setLocality(address.getLocality());
			savedEntity.setMobileNumber(address.getMobileNumber());
			savedEntity.setName(address.getName());
			savedEntity.setPinCode(address.getPinCode());
			savedEntity.setState(address.getState());
			return addressRepository.save(savedEntity);
		}
		throw new Exception("Address is missing with the given id");
	}

}
