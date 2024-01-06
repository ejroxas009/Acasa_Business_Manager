package com.acasa.BusinessManager.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.acasa.BusinessManager.dto.IngredientRequestDTO;
import com.acasa.BusinessManager.dto.ProductRequestDTO;
import com.acasa.BusinessManager.model.Ingredient;
import com.acasa.BusinessManager.model.Product;
import com.acasa.BusinessManager.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepo;
	
	private final IngredientService ingredientService;
	
	

	public ProductServiceImpl(ProductRepository productRepo, IngredientService ingredientService) {
		super();
		this.productRepo = productRepo;
		this.ingredientService = ingredientService;
	}



	@Override
	public List<Product> getAllProducts() {
		
		return productRepo.findAll();
	}

	@Override
	public Product getProductById(Long productId) {
		
		return productRepo.findById(productId).orElseThrow();
	}

//	@Override
//	public Product addProduct(ProductRequestDTO product) {
//		Product newProduct = new Product(product);
//		List<Ingredient> ingredientList = new ArrayList<>();
//		for(String ingredientId : product.getIngredientsIdList()) {
//			Ingredient ingredient = ingredientService.getIngredientById(Long.parseLong(ingredientId));
//			ingredientList.add(ingredient);
//		}
//		newProduct.setIngredients(ingredientList);
//		newProduct.setCreated(LocalDateTime.now());
//		return productRepo.save(newProduct);
//	}
	
	@Override
	public Product addProduct(ProductRequestDTO product) {
		Product newProduct = new Product(product);
		List<Ingredient> ingredientList = new ArrayList<>();
		
		for(IngredientRequestDTO ingredientRequest : product.getIngredientRequest()) {
			Ingredient newIngredient = ingredientService.addIngredient(ingredientRequest);
			ingredientList.add(newIngredient);
		}
		
		newProduct.setIngredients(ingredientList);
		newProduct.setCreated(LocalDateTime.now());
		return productRepo.save(newProduct);
	}

}
