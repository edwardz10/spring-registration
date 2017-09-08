package com.example.model;

import org.springframework.data.domain.Page;

public class ProductsWrapper {

	private Page<Product> products;
	
	public ProductsWrapper() {
	}

	public ProductsWrapper(Page<Product> products) {
		this.products = products;
	}

	public Page<Product> getProducts() {
		return products;
	}

	public void setProducts(Page<Product> products) {
		this.products = products;
	}

}
