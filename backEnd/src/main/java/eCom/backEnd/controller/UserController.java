package eCom.backEnd.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import eCom.backEnd.dao.repository.UserRepository;
import eCom.backEnd.entity.Users;
import eCom.backEnd.message.SuccessResponse;
import eCom.backEnd.service.UserService;

@RestController
@RequestMapping("/ecom/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public ResponseEntity<Users> registerUser(@RequestBody Users user) throws Exception {
		HashMap<String, String> output = new HashMap<>();

		List<Users> userFromDB = userRepository.findUsersByUserName(user.getUserName());
		if (userFromDB.size() > 0) {
			return new ResponseEntity<Users>(user, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole("USER");
		user.setActive(1);
		userRepository.save(user);
		return new ResponseEntity<Users>(user,
				HttpStatus.CREATED);
	}

	@GetMapping("/authenticate")
	public Users authenticateUser(Authentication authentication) {
		List<Users> userFromDB = userRepository.findUsersByUserName(authentication.getName());
		if (userFromDB.size() > 0) {
			return userFromDB.get(0);
		}
		return null;
	}

	@PutMapping("/updateUser")
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

	@DeleteMapping("delete/{userName}")
	public ResponseEntity<SuccessResponse> deleteUser(@PathVariable String userName) throws Exception {
		SuccessResponse success = new SuccessResponse(userRepository.deleteUserByUserName(userName));
		return new ResponseEntity<>(success, HttpStatus.OK);
	}

}
