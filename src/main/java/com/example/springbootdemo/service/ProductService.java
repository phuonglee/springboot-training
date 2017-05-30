package com.example.springbootdemo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;

import com.example.springbootdemo.model.Product;

public interface ProductService {
	Product findById(Long id);
	 
    Product findByName(String name);
 
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void saveProduct(Product product);
 
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateProduct(Product product);
 
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteProductById(Long id);
 
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteAllProducts();
 
    List<Product> findAllProducts();
 
    boolean isProductExist(Product product);
    
    Page<Product> findPaginated(int page, int size);
}
