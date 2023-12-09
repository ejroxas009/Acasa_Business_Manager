//package com.acasa.BusinessManager.service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.springframework.stereotype.Service;
//
//import com.acasa.BusinessManager.dto.ProductSoldRequestDTO;
//import com.acasa.BusinessManager.model.Ingredient;
//import com.acasa.BusinessManager.model.Order;
//import com.acasa.BusinessManager.model.Product;
//import com.acasa.BusinessManager.model.ProductSold;
//import com.acasa.BusinessManager.repository.ProductSoldRepository;
//
//@Service
//public class ProductSoldServiceImpl implements ProductSoldService{
//
//	private final ProductSoldRepository productSoldRepo;
//	
//	private final ProductService productService;
//	
//	private final RawMaterialInventoryService rawMaterialInventoryService;
//	
////	private final OrderService orderService;
//
//	
//
//	
//	
//	
//	
//	public ProductSoldServiceImpl(ProductSoldRepository productSoldRepo, ProductService productService,
//			RawMaterialInventoryService rawMaterialInventoryService) {
//		super();
//		this.productSoldRepo = productSoldRepo;
//		this.productService = productService;
//		this.rawMaterialInventoryService = rawMaterialInventoryService;
//	}
//
//
//
//
//	@Override
//	public List<ProductSold> getAllProductsSold() {
//		return productSoldRepo.findAll();
//	}
//
//	
//
//	@Override
//	public ProductSold getProductSoldId(Long productSoldId) {
//		return productSoldRepo.findById(productSoldId).orElseThrow();
//	}
//
//	@Override
//	public ProductSold addProductSold(ProductSoldRequestDTO productSold, Long orderId) {
//		ProductSold newProductSold = new ProductSold();
//		Product product = productService.getProductById(productSold.getProductId());
//		List<Ingredient> ingredientList = product.getIngredients();
//		for(Ingredient ingrediet: ingredientList) {
//			rawMaterialInventoryService.deductInventory(ingrediet, productSold.getQuantity());
//		}
////		Order order = orderService.getOrderById(orderId);
//		
//		newProductSold.setProduct(product);
//		newProductSold.setQuantity(productSold.getQuantity());
////		newProductSold.setOrder(order);
//		newProductSold.setIsCollected(false);
//		newProductSold.setTotalPrice(product.getProductPrice()* productSold.getQuantity());
//		newProductSold.setCreated(LocalDateTime.now());
//		return productSoldRepo.save(newProductSold);
//	}
//
//	@Override
//	public ProductSold collectIncome(Long productSoldId) {
//		ProductSold productSoldInDb = this.getProductSoldId(productSoldId);
//		productSoldInDb.setIsCollected(true);
//		
//		return productSoldRepo.save(productSoldInDb);
//	}
//	
//	
//
//}
