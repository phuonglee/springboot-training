package com.example.springbootdemo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.springbootdemo.model.Product;

public interface ProductService {
	Product findById(Long id);
	 
    Product findByName(String name);
 
    void saveProduct(Product product);
 
    void updateProduct(Product product);
 
    void deleteProductById(Long id);
 
    void deleteAllProducts();
 
    List<Product> findAllProducts();
 
    boolean isProductExist(Product product);
    
    Page<Product> findPaginated(int page, int size);
}
