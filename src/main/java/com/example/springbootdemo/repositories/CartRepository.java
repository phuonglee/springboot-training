package com.example.springbootdemo.repositories;

import com.example.springbootdemo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllCartByUserId(Long id);

    List<Cart> findAllCartByProductId(Long id);

    Cart findByProductId(Long id);
}
