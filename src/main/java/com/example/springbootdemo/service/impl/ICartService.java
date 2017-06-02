package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.model.Cart;
import com.example.springbootdemo.model.Product;
import com.example.springbootdemo.repositories.CartRepository;
import com.example.springbootdemo.repositories.ProductRepository;
import com.example.springbootdemo.service.CartService;
import com.example.springbootdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("cartService")
@Transactional
public class ICartService implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Override
	public Cart findById(Long id) {
		return cartRepository.findOne(id);
	}

	@Override
	public Cart findByProductId(Long id) {
		return cartRepository.findByProductId(id);
	}

	@Override
	public void saveCart(Cart cart) {
		cartRepository.save(cart);
	}

	@Override
	public void updateCart(Cart cart) {
		saveCart(cart);
	}

	@Override
	public void deleteCartById(Long id) {
		cartRepository.delete(id);
	}

	@Override
	public void deleteAllCarts() {
		cartRepository.deleteAll();
	}

	@Override
	public List<Cart> findAllCarts() {
		return cartRepository.findAll();
	}

	@Override
	public List<Cart> findAllCartByUserId(Long id) {
		return cartRepository.findAllCartByUserId(id);
	}

	@Override
	public boolean isProductExistInCart(Product product) {
		return cartRepository.findAllCartByProductId(product.getId()).size() > 0;
	}
}
