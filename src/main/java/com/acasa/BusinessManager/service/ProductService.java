package com.acasa.BusinessManager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acasa.BusinessManager.dto.ProductRequestDTO;
import com.acasa.BusinessManager.model.Product;

@Service
public interface ProductService {

	List<Product> getAllProducts();
	Product getProductById(Long productId);
	Product addProduct(ProductRequestDTO product);
}
