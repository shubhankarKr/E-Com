package eCom.backEnd.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eCom.backEnd.entity.Address;

public interface AddressRepository  extends JpaRepository<Address, Integer> {

}
