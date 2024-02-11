package eCom.backEnd.service;

import java.util.Set;

import eCom.backEnd.entity.Cart;
import eCom.backEnd.entity.Products;

public interface CartService {
	public Cart findByUserName();

	public Cart add(Products product) throws Exception;

	public Cart remove(Products product) throws Exception;

	public Cart updateCartProducts(Set<Products> products) throws Exception;

}
