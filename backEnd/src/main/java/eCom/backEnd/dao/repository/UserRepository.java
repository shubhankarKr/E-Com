package eCom.backEnd.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eCom.backEnd.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	Users findUsersByEmail(String email);

	Optional<Users> findById(int id);

	@Query("select u from Users u where u.active = 1 and u.id =?1")
	Optional<Users> findActiveUserById(int id);

	@Query("select u from Users u where u.active = 1 and u.userName =?1")
	Optional<Users> findActiveUserByUserName(String userName);

	@Query("update Users u set u.active = 0 where u.id = ?1")
	Optional<Users> deleteUserById(int id);

	@Query("update Users u set u.active = 0 where u.userName = ?1")
	Optional<Users> deleteUserByUserName(String userName);

}
