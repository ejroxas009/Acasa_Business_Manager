//package com.acasa.BusinessManager.controller;
//
//import java.util.List;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.acasa.BusinessManager.dto.ProductSoldRequestDTO;
//import com.acasa.BusinessManager.model.ProductSold;
//import com.acasa.BusinessManager.service.ProductSoldService;
//
//@RestController
//@RequestMapping(value = "/api/product-sold")
//public class ProductSoldController {
//	
//	private final ProductSoldService productSoldService;
//	
//	
//
//	public ProductSoldController(ProductSoldService productSoldService) {
//		super();
//		this.productSoldService = productSoldService;
//	}
//
//
//
//	@GetMapping
//	public List<ProductSold> getAllProductsSold(){
//		return productSoldService.getAllProductsSold();
//	}
//	
//	@GetMapping(value = "/{productSoldId}")
//	public ProductSold getProductSoldById(@PathVariable Long productSoldId) {
//		return productSoldService.getProductSoldId(productSoldId);
//	}
//	
//	@PostMapping(value = "/{orderId}")
//	public ProductSold addProductSold(@RequestBody ProductSoldRequestDTO productSold, @PathVariable Long orderId) {
//		return productSoldService.addProductSold(productSold, orderId);
//	}
//	
//	@PutMapping(value = "/collect-income/{productSoldId}")
//	public ProductSold collectIncome(@PathVariable Long productSoldId) {
//		return productSoldService.collectIncome(productSoldId);
//	}
//	
//}
