package eCom.backEnd.dao;

import java.util.Set;

import eCom.backEnd.entity.Cart;
import eCom.backEnd.entity.Products;

public interface CartDao {
	public Cart add(Products products) throws Exception;

	public Cart remove(Products products) throws Exception;

	public Cart updateCartProducts(Set<Products> products) throws Exception;
}
