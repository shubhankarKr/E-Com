package eCom.backEnd.daoImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eCom.backEnd.dao.CartDao;
import eCom.backEnd.dao.repository.CartRepository;
import eCom.backEnd.dao.repository.ProductRepository;
import eCom.backEnd.entity.Cart;
import eCom.backEnd.entity.Products;
import eCom.backEnd.service.UserAuthenticationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Repository
public class CartDaoImpl implements CartDao {

	@Autowired
	UserAuthenticationService authenticationService;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	EntityManager entityManager;

	@Autowired
	ProductRepository productRepository;

	@Override
	public Cart add(Products product) throws Exception {
		Query findQuery = entityManager.createQuery("select c from Cart c where c.userName =:userName");
		findQuery.setParameter("userName", authenticationService.getCurrentUser());

		Cart saved = (Cart) findQuery.getSingleResult();
		if (saved == null) {
			Cart emptyCart = new Cart();
			emptyCart.setUserName(authenticationService.getCurrentUser());
			cartRepository.save(emptyCart);
			saved = (Cart) findQuery.getSingleResult();
		}

		Optional<Products> productsEntity = productRepository.findById(product.getId());
		if (productsEntity.isPresent()) {
			saved.getProducts().add(productsEntity.get());
		}
		return saved;
	}

	@Override
	public Cart remove(Products product) throws Exception {
		Query findQuery = entityManager.createQuery("select c from Cart c where c.userName =:userName");
		findQuery.setParameter("userName", authenticationService.getCurrentUser());

		Cart saved = (Cart) findQuery.getSingleResult();
		Optional<Products> productsEntity = productRepository.findById(product.getId());
		if (productsEntity.isPresent()) {
			saved.getProducts().remove(productsEntity.get());
		}
		return saved;
	}

	@Override
	public Cart updateCartProducts(Set<Products> products) throws Exception {
		List<Integer> ids = products.stream().map((e) -> e.getId()).collect(Collectors.toList());
		List<Products> productsEntity = productRepository.findAllById(ids);

		if (products.size() != ids.size()) {
			throw new Exception("please check the updated products model");
		}
		Query findQuery = entityManager.createQuery("select c from Cart c where c.userName =:userName");
		findQuery.setParameter("userName", authenticationService.getCurrentUser());

		Cart saved = (Cart) findQuery.getSingleResult();
		saved.setProducts(new HashSet<>(productsEntity));
		return saved;
	}

}
