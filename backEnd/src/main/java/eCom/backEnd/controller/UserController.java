package eCom.backEnd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eCom.backEnd.dao.repository.AddressRepository;
import eCom.backEnd.dao.repository.AuthoriryRepository;
import eCom.backEnd.dao.repository.UserRepository;
import eCom.backEnd.entity.Authority;
import eCom.backEnd.entity.Users;
import eCom.backEnd.message.SuccessResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	AuthoriryRepository authoriryRepository;

	@Autowired
	Environment environment;

	@Autowired
	AddressRepository addressRepository;

	@PostMapping("/register")
	public Users registerUser(@Valid @RequestBody Users user) throws Exception {
		Optional<Users> entity = userRepository.findActiveUserByUserNameOrEmail(user.getUserName(), user.getEmail());
		if (entity.isPresent()) {
			if (entity.get().getEmail() != null) {
				throw new Exception(entity.get().getEmail() + " " + environment.getProperty("IS_ALREADY_REGISTERED"));
			}
			throw new Exception(entity.get().getUserName() + " " + environment.getProperty("IS_ALREADY_REGISTERED"));

		}
		user.setPassword(encoder.encode(user.getPassword()));
		if (user.getUserName() == null) {
			user.setUserName(user.getEmail().split("@")[0]);
		}
		if (user.getAuthorityList() == null || user.getAuthorityList().isEmpty()) {
			user.setAuthorityList(getDefaultAuthority());
		}
		Users savedUser = userRepository.save(user);
		return savedUser;
	}

	@GetMapping("/authenticate")
	public Users authenticateUser(Authentication authentication) throws Exception {
		Optional<Users> entity = userRepository.findActiveUserByUserNameOrEmail(authentication.getName(),
				authentication.getName());
		if (entity.isPresent()) {
			return entity.get();
		}
		return null;
	}

	@GetMapping("/findByUserNameOrEmail/{userNameOrEmail}")
	public Users authenticateUser(@PathVariable("userNameOrEmail") String userNameOrEmail) throws Exception {
		Optional<Users> entity = userRepository.findActiveUserByUserNameOrEmail(userNameOrEmail, userNameOrEmail);
		if (entity.isPresent()) {
			return entity.get();
		}
		throw new Exception(userNameOrEmail + " does not exists in the system");
	}

	@PutMapping("/updateUser")
	public Users updateUser(@RequestBody Users user) throws Exception {
		Users savedUser = null;
		Optional<Users> optionalUser = userRepository.findById(user.getId());
		if (!optionalUser.isEmpty()) {
			savedUser = optionalUser.get();
			savedUser.setPassword(encoder.encode(user.getPassword()));
			savedUser.setEmail(user.getEmail());
			savedUser.setUserName(user.getUserName());
			savedUser = userRepository.save(user);
		}
		return savedUser;
	}

	@DeleteMapping("/delete/userName/{userName}")
	public ResponseEntity<SuccessResponse> deleteUser(@PathVariable String userName) throws Exception {
		Optional<Users> optionalUser = userRepository.findActiveUserByUserName(userName);
		if (optionalUser.isPresent()) {
			userRepository.deleteUserByUserName(userName);
			return new ResponseEntity<>(
					new SuccessResponse(userName + " " + environment.getProperty("DELETED_SUCCESSFULLY")),
					HttpStatus.ACCEPTED);
		}
		throw new Exception(userName + " " + environment.getProperty("DOES_NOT_EXIST"));
	}

	@DeleteMapping("/delete/id/{id}")
	public ResponseEntity<SuccessResponse> deleteUser(@PathVariable int id) throws Exception {
		Optional<Users> optionalUser = userRepository.findActiveUserById(id);
		if (optionalUser.isPresent()) {
			userRepository.deleteUserById(id);
			return new ResponseEntity<>(new SuccessResponse(id + " " + environment.getProperty("DELETED_SUCCESSFULLY")),
					HttpStatus.ACCEPTED);
		}
		throw new Exception(id + " " + environment.getProperty("DOES_NOT_EXIST"));
	}

	@GetMapping("/allAuthorities")
	List<Authority> findAllAuthorities() {
		return authoriryRepository.findAll();
	}

	private List<Authority> getDefaultAuthority() {
		List<Authority> authorityList = new ArrayList<>();
		Authority a = new Authority();
		a.setName("ROLE_USER");
		authorityList.add(a);
		return authorityList;
	}
}
