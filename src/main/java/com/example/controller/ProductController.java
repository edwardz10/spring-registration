package com.example.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Pager;
import com.example.model.Product;
import com.example.service.ProductService;

@Controller
public class ProductController {

	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

	private ProductService productService;

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;

	@Value("${stocks.productsPerPage}")
	private int productsPerPage;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping(value="/products", method = RequestMethod.GET)
	public ModelAndView products(@RequestParam("page") Optional<Integer> page){
		ModelAndView modelAndView = new ModelAndView("products");

		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Product> products = productService.getAllProductsPageable(new PageRequest(evalPage, productsPerPage));
		Pager pager = new Pager(products.getTotalPages(), products.getNumber(), BUTTONS_TO_SHOW);

		modelAndView.addObject("products", products);
		modelAndView.addObject("pager", pager);
		return modelAndView;
	}

	@RequestMapping(value="/addbet", method = RequestMethod.POST)
//	public ModelAndView addBet(ModelAndView modelAndView, @Valid Product product, @RequestParam("paramName") String id, BindingResult result) {
	public ModelAndView addBet(ModelAndView modelAndView, @RequestParam("paramName") String id, BindingResult result) {
//		LOG.info("product: " + product);
		LOG.info("id: " + id);
//	public ModelAndView addBet(@ModelAttribute Long productId){
//		LOG.info("Adding a bet for product " + productId);
//		ModelAndView modelAndView = new ModelAndView("addbet");
//		Product selectedProduct = productService.getProductById(productId);
//		LOG.info("Found a product " + selectedProduct);
//		modelAndView.addObject("product", product);
		return modelAndView;
	}
}
