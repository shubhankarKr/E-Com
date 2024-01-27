package eCom.backEnd.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eCom.backEnd.entity.Authority;

public interface AuthoriryRepository  extends JpaRepository<Authority, Integer>{
	List<Authority> findAllByMd(int md);
}
