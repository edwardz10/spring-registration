package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.mifmif.common.regex.Generex;

@Service
public class ProductGenerateService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductGenerateService.class);

	private ProductRepository productRepository;
	private String regexp = "[A-Z]{2,6}[0-9]{4}";
	private Generex generator;
	private Random rnd;

	@Autowired
	public ProductGenerateService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}	
	
	@Value("${stocks.productAmount}")
	private Long productAmount;

//	private List<String> names;
	
	@PostConstruct
	public void initialize() {
//		names = new ArrayList<>();
		generator = new Generex(regexp);
		rnd = new Random();
		
		generateAndSaveProducts();
	}

	private void generateAndSaveProducts() {
		long availableProducts = productRepository.count();
		long productsMissing = productAmount - availableProducts;

		LOG.info(availableProducts + " products are available; " + productsMissing + " we have to generate");
		
		for (long i = 0; i < productsMissing; i++) {
			Product product = generateProduct();
			LOG.info("Generated a new Product: " + product);
			productRepository.save(product);
		}
	}

	private Product generateProduct() {
		String randomName = generateName();
		Float randomPrice = generatePrice();
		return new Product(randomName, randomPrice);
	}

	private String generateName() {
		String name;
		Product existingProduct;

		do {
			name = generator.random();
			existingProduct = productRepository.findByName(name);
			if (existingProduct != null) {
				LOG.warn("The product '" + name + "' already exists, regenerate...");
			}
		} while (existingProduct != null);
		
		return name;
	}

	private Float generatePrice() {
		return rnd.nextFloat() * 100.0f;
	}
	
}
