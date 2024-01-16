package eCom.backEnd.dao;

import java.util.List;

import eCom.backEnd.entity.Users;

public interface UserDao {
	String deleteUser(String userName) throws Exception;

	List<Users> findActiveUser(String userName) throws Exception;
}
