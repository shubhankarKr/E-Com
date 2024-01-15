package eCom.backEnd.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eCom.backEnd.dao.UserDao;
import eCom.backEnd.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer>, UserDao {
	public List<Users> findUsersByUserName(String userName);
}
