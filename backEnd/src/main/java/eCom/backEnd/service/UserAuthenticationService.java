package eCom.backEnd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import eCom.backEnd.dao.UserDao;
import eCom.backEnd.entity.Authority;
import eCom.backEnd.entity.Users;

@Service
public class UserAuthenticationService implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Users> users;
		User result = null;
		try {
			users = userDao.findActiveUser(username);
			if (users.size() == 0) {
				throw new UsernameNotFoundException(username+" is not registered");
			} else {
				result = new User(users.get(0).getUserName(), users.get(0).getPassword(), getGrantedAuthorities(users.get(0).getAuthorityList()));
			}
		} catch (UsernameNotFoundException e) {
//			e.printStackTrace();
			throw e;
		}
		return result;
	}
	
	public List<GrantedAuthority> getGrantedAuthorities(List<Authority> authorities){
		List<GrantedAuthority> authList=new ArrayList<>();
		for(Authority auth:authorities) {
			authList.add(new SimpleGrantedAuthority(auth.getName()));
		}
		return authList;
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
