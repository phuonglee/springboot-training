package com.example.springbootdemo.controller.restapi;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.springbootdemo.exeption.MyResourceNotFoundException;
import com.example.springbootdemo.model.Product;
import com.example.springbootdemo.service.ProductService;

@RestController
@RequestMapping("/api")
public class RAProductController {

	public static final Logger logger = LoggerFactory.getLogger(RAProductController.class);

	// Service which will do all data
	@Autowired
	ProductService productService;

	/**
	 * Retrieve All Products.
	 * @return ResponseEntity<List<Product>> response list of Product object.
	 */
	@RequestMapping(value = "/product/", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> listAllProducts() {
		List<Product> products = productService.findAllProducts();
		if (products.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(products);
	}

	/**
	 * Retrieve Single Product.
	 * @param id of product to retrieve.
	 * @return ResponseEntity<Product> response entity of product object.
	 */
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
		logger.info("Fetching Product with id {}", id);
		Product product = productService.findById(id);
		if (product == null) {
			logger.error("Product with id {} not found.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
			
		}
		return  ResponseEntity.ok(product);
	}

	/**
	 * Create a Product.
	 * @param product data to create.
	 * @param ucBuilder refer to {@link UriComponentsBuilder}.
	 * @return ResponseEntity<String> header uri to response.
	 */
	@RequestMapping(value = "/product/", method = RequestMethod.POST)
	public ResponseEntity<String> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Product : {}", product);

		if (productService.isProductExist(product)) {
			logger.error("Unable to create. A product with name {} already exist", product.getProductName());
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		productService.saveProduct(product);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/product/{id}").buildAndExpand(product.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	/**
	 * Update a Product.
	 * @param id of product to update.
	 * @param product object store data to update.
	 * @return ResponseEntity<Product> response entity of product object updated.
	 */
	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		logger.info("Updating Product with id {}", id);

		Product currentProduct = productService.findById(id);

		if (currentProduct == null) {
			logger.error("Unable to update. Product with id {} not found.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

		currentProduct.setProductName(product.getProductName());
		currentProduct.setProductDetails(product.getProductDetails());
		currentProduct.setPrice(product.getPrice());
		currentProduct.setQuantityThreshold(product.getQuantityThreshold());

		productService.updateProduct(currentProduct);
		return ResponseEntity.ok(currentProduct);
	}

	/**
	 * Delete a Product.
	 * @param id of product to delete.
	 * @return ResponseEntity<Product> response with no content after deleted.
	 */
	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Product with id {}", id);

		Product product = productService.findById(id);
		if (product == null) {
			logger.error("Unable to delete. Product with id {} not found.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		productService.deleteProductById(id);
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete All Products.
	 * @return ResponseEntity<Product> response product object.
	 */
	@RequestMapping(value = "/product/", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteAllProducts() {
		logger.info("Deleting All Products");

		productService.deleteAllProducts();
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Retrieve list of products paginated.
	 * @param page number of page to retrieve.
	 * @param size of list data on each page.
	 * @return Page<Product> data list with paginated.
	 */
	@RequestMapping(value = "/product/get", params = { "page", "size" }, method = RequestMethod.GET)
	public Page<Product> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
		Page<Product> resultPage = productService.findPaginated(page, size);
		if (page > resultPage.getTotalPages()) {
			throw new MyResourceNotFoundException();
		}
		return resultPage;
	}
}
