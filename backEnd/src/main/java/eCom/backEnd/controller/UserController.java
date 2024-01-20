package eCom.backEnd.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eCom.backEnd.dao.UserDao;
import eCom.backEnd.dao.repository.AuthoriryRepository;
import eCom.backEnd.dao.repository.UserRepository;
import eCom.backEnd.entity.Authority;
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
	UserDao userDao;

	@Autowired
	UserService userService;
	
	@Autowired
	AuthoriryRepository authoriryRepository;

	@PostMapping("/register")
	public Users registerUser(@RequestBody Users user) throws Exception {
		List<Users> usersLists = userDao.findActiveUser(user.getUserName());
		if (usersLists.size() > 0) {
			throw new Exception(user.getUserName() + " is already registered");
		}
		user.setPassword(encoder.encode(user.getPassword()));
		user.setActive(1);
		Users savedUser=userRepository.save(user);
		List<Authority> authorityList= user.getAuthorities();
		if(user.getAuthorities() !=null && !authorityList.isEmpty()) {
			for (Authority authority : authorityList) {
				authority.setUser(savedUser);
			}
		}else {
			authorityList= new ArrayList<>();
			Authority a= new Authority();
			a.setName("ROLE_USER");
			a.setUser(savedUser);
			authorityList.add(a);
		}
		authoriryRepository.saveAll(authorityList);
		return user;
	}

	@GetMapping("/authenticate")
	public Users authenticateUser(Authentication authentication) throws Exception {
		List<Users> userFromDB = userDao.findActiveUser(authentication.getName());
		if (userFromDB.size() > 0) {
			return userFromDB.get(0);
		} else {
			throw new Exception(authentication.getName() + " is not registered");
		}
	}

	@PutMapping("/updateUser")
	public Users updateUser(@RequestBody Users user) throws Exception {
		Users savedUser = null;
		List<Users> userFromDB = userDao.findActiveUser(user.getUserName());
		if (userFromDB.size() > 0) {
			user.setPassword(encoder.encode(user.getPassword()));
			savedUser = userRepository.save(user);
		}
		return savedUser;
	}

	@DeleteMapping("delete/{userName}")
	public ResponseEntity<SuccessResponse> deleteUser(@PathVariable String userName) throws Exception {
		SuccessResponse success = new SuccessResponse(userService.deleteUser(userName));
		return new ResponseEntity<>(success, HttpStatus.OK);
	}
	
	@GetMapping("/allAuthorities")
	List<Authority> findAllAuthorities(){
		return authoriryRepository.findAll();
	}
}
