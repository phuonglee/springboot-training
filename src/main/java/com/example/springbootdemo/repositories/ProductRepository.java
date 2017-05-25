package com.example.springbootdemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootdemo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	Product findByProductName(String name);
}
