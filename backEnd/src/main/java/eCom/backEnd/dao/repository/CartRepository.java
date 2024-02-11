package eCom.backEnd.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eCom.backEnd.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

	public Cart findByUserName(String userName);

}
