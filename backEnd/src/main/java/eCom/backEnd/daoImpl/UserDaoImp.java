package eCom.backEnd.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eCom.backEnd.dao.UserDao;
import eCom.backEnd.entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	EntityManager entityManager;

	@Override
	public String deleteUser(String userName) throws Exception {
		List<Users> usersList = findActiveUser(userName);
		if (usersList.size() == 0) {
			throw new Exception(userName + " does not exists ");
		}
		Users user = usersList.get(0);
		user.setActive(0);
		return (userName + " deleted successfully");
	}

	@Override
	public List<Users> findActiveUser(String userName) {
		Query query = entityManager.createQuery("select u from Users u where userName = : userName AND active=:active");
		query.setParameter("userName", userName);
		query.setParameter("active", 1);
		List<Users> users = query.getResultList();
		return users;
	}
}
