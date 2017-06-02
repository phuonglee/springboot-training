package com.example.springbootdemo.service;

import com.example.springbootdemo.model.Cart;
import com.example.springbootdemo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface CartService {
	Cart findById(Long id);

    Cart findByProductId(Long id);

    void saveCart(Cart cart);
 
    void updateCart(Cart cart);
 
    void deleteCartById(Long id);
 
    void deleteAllCarts();
 
    List<Cart> findAllCarts();

    List<Cart> findAllCartByUserId(Long id);
 
    boolean isProductExistInCart(Product product);

}
