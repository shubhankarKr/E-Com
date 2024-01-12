package eCom.backEnd.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eCom.backEnd.dao.repository.UserRepository;
import eCom.backEnd.entity.Users;

@RestController
@RequestMapping("/ecom/users")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@PostMapping("/register")
	public ResponseEntity<HashMap<String, String>> registerUser(@RequestBody Users users) throws Exception {
		HashMap<String, String> output = new HashMap<>();

		List<Users> userFromDB = userRepository.findUsersByUserName(users.getUserName());
		if (userFromDB.size() > 0) {
			output.put("error", userFromDB.get(0).getUserName() + " already exists");
			return new ResponseEntity<HashMap<String, String>>(output, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		users.setPassword(encoder.encode(users.getPassword()));
		users.setRole("USER");
		users.setActive(1);
		userRepository.save(users);
		output.put("success", users.getUserName() + " created successfully");
		return new ResponseEntity<HashMap<String, String>>(output, HttpStatus.CREATED);
	}

	@GetMapping("/authenticate")
	public Users authenticateUser(Authentication authentication) {
		List<Users> userFromDB = userRepository.findUsersByUserName(authentication.getName());
		if (userFromDB.size() > 0) {
			return userFromDB.get(0);
		}
		return null;
	}

	@PostMapping("/user")
	public ResponseEntity<HashMap<String, Object>> updateUser(@RequestBody Users user) {
		HashMap<String, Object> output = new HashMap<>();
		List<Users> userFromDB = userRepository.findUsersByUserName(user.getUserName());
		if (userFromDB.size() > 0) {
			user.setPassword(encoder.encode(user.getPassword()));
			Users savedUser = userRepository.save(user);
			output.put("success", savedUser);
			return new ResponseEntity<HashMap<String,Object>>(output, HttpStatus.OK);
		}
		output.put("error", user.getUserName()+" does not exists");
		return new ResponseEntity<HashMap<String,Object>>(output,HttpStatus.NOT_FOUND);
	}

}
