package com.acasa.BusinessManager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acasa.BusinessManager.dto.ProductRequestDTO;
import com.acasa.BusinessManager.model.Product;
import com.acasa.BusinessManager.service.ProductService;

@RestController
@RequestMapping(value = "/api/product")
public class ProductController {
	
	private final ProductService productService;
	
	

	public ProductController(ProductService productService) {
		super();
		this.productService = productService;
	}



	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	
	@GetMapping(value = "/{productId}")
	public Product getProductById(@PathVariable Long productId) {
		return productService.getProductById(productId);
	}
	
	@PostMapping
	public Product addProduct(@RequestBody ProductRequestDTO product) {
		return productService.addProduct(product);
	}
}
