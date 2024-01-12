package eCom.backEnd.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import eCom.backEnd.entity.Users;

public interface UserRepository extends CrudRepository<Users, Integer> {
	public List<Users> findUsersByUserName(String userName);
}
