package eCom.backEnd.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eCom.backEnd.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
	Users findUsersByEmail(String email);
}
