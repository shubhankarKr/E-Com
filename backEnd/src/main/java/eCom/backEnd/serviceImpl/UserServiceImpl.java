package eCom.backEnd.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eCom.backEnd.dao.UserDao;
import eCom.backEnd.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier("userDaoImp")
	UserDao userDao;


	@Override
	public String deleteGivenUserByUserName(String userName) throws Exception {
		return userDao.deleteGivenUserByUserName(userName);
	}


}
