package eCom.backEnd.serviceImpl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eCom.backEnd.dao.CartDao;
import eCom.backEnd.dao.repository.CartRepository;
import eCom.backEnd.entity.Cart;
import eCom.backEnd.entity.Products;
import eCom.backEnd.service.CartService;
import eCom.backEnd.service.UserAuthenticationService;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	CartDao cartDao;

	@Autowired
	UserAuthenticationService authenticationService;

	@Override
	public Cart findByUserName() {
		Cart cart = cartRepository.findByUserName(authenticationService.getCurrentUser());
		if (cart == null) {
			cart = new Cart();
			cart.setUserName(authenticationService.getCurrentUser());
			return cartRepository.save(cart);
		}
		return cart;
	}

	@Override
	public Cart add(Products product) throws Exception {
		return cartDao.add(product);
	}

	@Override
	public Cart remove(Products product) throws Exception {
		return cartDao.remove(product);
	}

	@Override
	public Cart updateCartProducts(Set<Products> products) throws Exception {
		return cartDao.updateCartProducts(products);
	}

}
