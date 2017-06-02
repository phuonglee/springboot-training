package com.example.springbootdemo.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Set;


/**
 * The persistent class for the PRODUCTS database table.
 * 
 */
@Entity
@Table(name="PRODUCTS")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Long id;

	@Column(precision=10)
	private BigDecimal price;

	@Column(name="product_details", length=1000)
	private String productDetails;

	@Column(name="product_name", length=250)
	private String productName;

	@Column(name="quantity_threshold")
	private BigInteger quantityThreshold;

	public Product() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getProductDetails() {
		return this.productDetails;
	}

	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigInteger getQuantityThreshold() {
		return this.quantityThreshold;
	}

	public void setQuantityThreshold(BigInteger quantityThreshold) {
		this.quantityThreshold = quantityThreshold;
	}
}