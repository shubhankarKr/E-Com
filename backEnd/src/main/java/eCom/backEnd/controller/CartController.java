package eCom.backEnd.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eCom.backEnd.entity.Cart;
import eCom.backEnd.entity.Products;
import eCom.backEnd.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;

	@GetMapping(value = "/find")
	public Cart findByUserName() {
		return cartService.findByUserName();
	}

	@PostMapping("/add")
	public Cart add(@RequestBody Products product) throws Exception {
		return cartService.add(product);
	}

	@PostMapping("/remove")
	public Cart remove(@RequestBody Products product) throws Exception {
		return cartService.remove(product);
	}

	@PutMapping("/update")
	public Cart updateCartProducts(@RequestBody Set<Products> products) throws Exception {
		return cartService.updateCartProducts(products);
	}

}
