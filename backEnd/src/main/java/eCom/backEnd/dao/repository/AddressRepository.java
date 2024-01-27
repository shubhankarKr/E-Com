package eCom.backEnd.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import eCom.backEnd.entity.Address;

public interface AddressRepository  extends JpaRepository<Address, Integer> {
	
	@Transactional
	@Modifying
	@Query("update Address a set a.active = 0 where a.id = ?1" )
	public int deleteAddress(int id);
}
