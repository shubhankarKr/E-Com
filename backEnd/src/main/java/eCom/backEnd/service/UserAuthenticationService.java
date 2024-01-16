package eCom.backEnd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import eCom.backEnd.dao.UserDao;
import eCom.backEnd.entity.Users;

@Service
public class UserAuthenticationService implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) {
		List<Users> users;
		User result = null;
		try {
			users = userDao.findActiveUser(username);
			if (users.size() == 0) {
				throw new UsernameNotFoundException("User Does not exists!");
			} else {
				List<GrantedAuthority> dbAuths = new ArrayList<>();
				result = new User(users.get(0).getUserName(), users.get(0).getPassword(), dbAuths);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public String getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof String) {
			return (String) principal;
		} else {
			return null;
		}
	}

}
