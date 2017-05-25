package com.example.springbootdemo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootdemo.model.Product;
import com.example.springbootdemo.repositories.ProductRepository;
import com.example.springbootdemo.service.ProductService;

@Service("productService")
@Transactional
public class IProductService implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product findById(Long id) {
		return productRepository.findOne(id);
	}

	public Product findByName(String name) {
		return productRepository.findByProductName(name);
	}

	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	public void updateProduct(Product product) {
		saveProduct(product);
	}

	public void deleteProductById(Long id) {
		productRepository.delete(id);
	}

	public void deleteAllProducts() {
		productRepository.deleteAll();
	}

	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	public boolean isProductExist(Product product) {
		return findByName(product.getProductName()) != null;
	}

	@Override
	public Page<Product> findPaginated(int page, int size) {
		return productRepository.findAll(new PageRequest(page, size));
	}

}
