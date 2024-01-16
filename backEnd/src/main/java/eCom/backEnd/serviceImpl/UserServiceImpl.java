package eCom.backEnd.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eCom.backEnd.dao.UserDao;
import eCom.backEnd.service.UserService;
import jakarta.persistence.EntityManager;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Autowired
	EntityManager entityManager;

	@Override
	public String deleteUser(String userName) throws Exception {
		return userDao.deleteUser(userName);
	}

}
