package eCom.backEnd.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import eCom.backEnd.dao.UserDao;
import eCom.backEnd.dao.repository.UserRepository;
import eCom.backEnd.entity.Users;
import jakarta.persistence.EntityManager;

public class UserDaoImp implements UserDao {

	@Autowired
	EntityManager entityManager;

	@Autowired
	UserRepository userRepository;

	@Override
	public String deleteUserByUserName(String userName) throws Exception {
		List<Users> usersList = userRepository.findUsersByUserName(userName);
		if (usersList.size() == 0) {
			throw new Exception(userName + " does not exists ");
		}
		Users user = usersList.get(0);
		user.setActive(0);
		entityManager.persist(user);
		return userName + " deleted successfully";
	}

}
