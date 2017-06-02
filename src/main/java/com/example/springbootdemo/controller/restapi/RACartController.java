package com.example.springbootdemo.controller.restapi;

import com.example.springbootdemo.model.Cart;
import com.example.springbootdemo.model.Product;
import com.example.springbootdemo.model.User;
import com.example.springbootdemo.service.CartService;
import com.example.springbootdemo.service.ProductService;
import com.example.springbootdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RACartController {

	public static final Logger logger = LoggerFactory.getLogger(RACartController.class);

	// Service which will do all data
	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;

	/**
	 * Retrieve All Products.
	 * @return ResponseEntity<List<Product>> response list of Product object.
	 */
	@RequestMapping(value = "/cart/", method = RequestMethod.GET)
	public ResponseEntity<List<Product>> listAllProductsInCart() {
		List<Cart> cartList = cartService.findAllCartByUserId(1L);
		List<Product> productsInCart = new ArrayList<>();
		for (Cart c : cartList) {
			productsInCart.add(c.getProduct());
		}
		if (productsInCart.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.ok(productsInCart);
	}

//	/**
//	 * Retrieve Single Product.
//	 * @param id of product to retrieve.
//	 * @return ResponseEntity<Product> response entity of product object.
//	 */
//	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
//	public ResponseEntity<Product> getProduct(@PathVariable("id") long id) {
//		logger.info("Fetching Product with id {}", id);
//		Product product = productService.findById(id);
//		if (product == null) {
//			logger.error("Product with id {} not found.", id);
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//
//		}
//		return  ResponseEntity.ok(product);
//	}

	/**
	 *
	 * @param selectedProducts
	 * @param ucBuilder
	 * @return
	 */
	@RequestMapping(value = "/cart/", method = RequestMethod.POST)
	public ResponseEntity<String> createCart(@RequestBody List<Product> selectedProducts, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Cart with selected products: {}", selectedProducts);

		for (Product p : selectedProducts) {
			if (cartService.isProductExistInCart(p)) {
				logger.error("Unable to create. A product with id {} already exist in cart", p.getId());
//				return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
			} else {
				Cart newCart = new Cart();
				User currentUser = userService.findById(1L);
				newCart.setProduct(p);
				newCart.setUser(currentUser);
				newCart.setQuantity(BigInteger.ONE);
				cartService.saveCart(newCart);
			}
		}

//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/api/cart/{id}").buildAndExpand(c.getId()).toUri());
//		return new ResponseEntity<>(headers, HttpStatus.CREATED);
		return ResponseEntity.ok(null);
	}

//	/**
//	 * Update a Product.
//	 * @param id of product to update.
//	 * @param product object store data to update.
//	 * @return ResponseEntity<Product> response entity of product object updated.
//	 */
//	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT)
//	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
//		logger.info("Updating Product with id {}", id);
//
//		Product currentProduct = productService.findById(id);
//
//		if (currentProduct == null) {
//			logger.error("Unable to update. Product with id {} not found.", id);
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//		}
//
//		currentProduct.setProductName(product.getProductName());
//		currentProduct.setProductDetails(product.getProductDetails());
//		currentProduct.setPrice(product.getPrice());
//		currentProduct.setQuantityThreshold(product.getQuantityThreshold());
//
//		productService.updateProduct(currentProduct);
//		return ResponseEntity.ok(currentProduct);
//	}

	/**
	 * Delete a Product.
	 * @param id of product to delete.
	 * @return ResponseEntity<Product> response with no content after deleted.
	 */
	@RequestMapping(value = "/cart/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cart> deleteProduct(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Cart with id {}", id);

		Cart cart = cartService.findByProductId(id);
		if (cart == null) {
			logger.error("Unable to delete. Cart with product id {} not found.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		cartService.deleteCartById(cart.getId());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

//	/**
//	 * Delete All Products.
//	 * @return ResponseEntity<Product> response product object.
//	 */
//	@RequestMapping(value = "/product/", method = RequestMethod.DELETE)
//	public ResponseEntity<Product> deleteAllProducts() {
//		logger.info("Deleting All Products");
//
//		productService.deleteAllProducts();
//		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
//	}
//
//	/**
//	 * Retrieve list of products paginated.
//	 * @param page number of page to retrieve.
//	 * @param size of list data on each page.
//	 * @return Page<Product> data list with paginated.
//	 */
//	@RequestMapping(value = "/product/get", params = { "page", "size" }, method = RequestMethod.GET)
//	public Page<Product> findPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
//		Page<Product> resultPage = productService.findPaginated(page, size);
//		if (page > resultPage.getTotalPages()) {
//			throw new MyResourceNotFoundException();
//		}
//		return resultPage;
//	}
}
