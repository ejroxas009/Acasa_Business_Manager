package com.acasa.BusinessManager.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.acasa.BusinessManager.dto.OrderRequestDTO;
import com.acasa.BusinessManager.dto.ProductSoldRequestDTO;
import com.acasa.BusinessManager.dto.RtsRequestDTO;
import com.acasa.BusinessManager.model.Ingredient;
import com.acasa.BusinessManager.model.Order;
import com.acasa.BusinessManager.model.Product;
import com.acasa.BusinessManager.model.ProductSold;
import com.acasa.BusinessManager.repository.OrderRepository;
import com.acasa.BusinessManager.repository.ProductSoldRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	
	private final OrderRepository orderRepo;
	
	private final ProductSoldRepository productSoldRepo;

	
	private final RawMaterialInventoryService rawMaterialInventoryService;
	
	private final ProductService productService;
	

	
	

	

	

	public OrderServiceImpl(OrderRepository orderRepo, ProductSoldRepository productSoldRepo,
			RawMaterialInventoryService rawMaterialInventoryService, ProductService productService) {
		super();
		this.orderRepo = orderRepo;
		this.productSoldRepo = productSoldRepo;
		this.rawMaterialInventoryService = rawMaterialInventoryService;
		this.productService = productService;
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}

	@Override
	public Order getOrderById(Long orderId) {
		return orderRepo.findById(orderId).orElseThrow();
	}

	@Override
	public Order addOrder(OrderRequestDTO orderRequest) {
		
		List<ProductSoldRequestDTO> productSoldRequestDTOList = orderRequest.getProductSoldDTO();
		List<ProductSold> productSoldList = new ArrayList<>();
		Order newOrder = orderRepo.save(new Order());
		
		if(productSoldList != null) {
			for(ProductSoldRequestDTO sold : productSoldRequestDTOList) {
				ProductSold productSold = this.addProductSold(sold, newOrder.getOrderId());
				productSoldList.add(productSold);
			}
		}
		
		newOrder.setCreated(LocalDateTime.now());
		newOrder.setCustomerName(orderRequest.getCustomerName());
		newOrder.setProductSoldList(productSoldList);
		newOrder.setExtraEarnings(orderRequest.getExtraEarnings());
		newOrder.setExtraEarningsDescription(orderRequest.getExtraEarningsDescription());
		newOrder.setExtraExpense(orderRequest.getExtraExpense());
		newOrder.setExtraExpenseDescription(orderRequest.getExtraExpenseDescription());
		newOrder.setIsCollected(false);
		newOrder.setIsRTS(false);
		newOrder.setTotalPrice(this.getOrderTotalPrice(productSoldList));
		
		return orderRepo.save(newOrder);
	}
	
	@Override
	public Order collectOrder(Long orderId) {
		Order orderInDb = this.getOrderById(orderId);
		orderInDb.setIsCollected(true);
		orderInDb.setIsRTS(false);
		return orderRepo.save(orderInDb);
	}
	
	@Override
	public Order markAsRts(RtsRequestDTO rtsRequest) {
		Order orderInDb = this.getOrderById(rtsRequest.getOrderId());
		orderInDb.setRtsLoss(rtsRequest.getShippingFee());
		orderInDb.setIsRTS(true);
		orderInDb.setIsCollected(false);
		return orderRepo.save(orderInDb);
	}
	
	
	private Double getOrderTotalPrice(List<ProductSold> productSoldList) {
		Double totalPrice = 0.0;
		for(ProductSold productSold : productSoldList) {
			totalPrice += productSold.getTotalPrice();
		}
		
		return totalPrice;
	}
	
	
	@Override
	public List<Order> getAllCollectedOrders() {
		
		return orderRepo.findAll()
				.stream()
				.filter(order -> order.getIsCollected() && !order.getIsRTS())
				.collect(Collectors.toList());
	}

	@Override
	public List<Order> getAllUncollectedOrders() {
		return orderRepo.findAll()
				.stream()
				.filter(order-> !order.getIsCollected() && !order.getIsRTS())
				.collect(Collectors.toList());
	}

	@Override
	public List<Order> getAllRts() {
		
		return orderRepo.findAll()
				.stream()
				.filter(order -> order.getIsRTS() && !order.getIsCollected())
				.collect(Collectors.toList());
	}
	
	
	//-----------------------------------Product Sold---------------------------------------------------
	
	
	@Override
	public List<ProductSold> getAllProductsSold() {
		return productSoldRepo.findAll();
	}

	

	@Override
	public ProductSold getProductSoldId(Long productSoldId) {
		return productSoldRepo.findById(productSoldId).orElseThrow();
	}

	@Override
	public ProductSold addProductSold(ProductSoldRequestDTO productSold, Long orderId) {
		ProductSold newProductSold = new ProductSold();
		Product product = productService.getProductById(productSold.getProductId());
		List<Ingredient> ingredientList = product.getIngredients();
		for(Ingredient ingrediet: ingredientList) {
			rawMaterialInventoryService.deductInventory(ingrediet, productSold.getQuantity());
		}
		Order order = this.getOrderById(orderId);
		
		newProductSold.setProduct(product);
		newProductSold.setQuantity(productSold.getQuantity());
		newProductSold.setOrder(order);
		newProductSold.setIsCollected(false);
		newProductSold.setTotalPrice(product.getProductPrice()* productSold.getQuantity());
		newProductSold.setCreated(LocalDateTime.now());
		return productSoldRepo.save(newProductSold);
	}

	@Override
	public ProductSold collectIncome(Long productSoldId) {
		ProductSold productSoldInDb = this.getProductSoldId(productSoldId);
		productSoldInDb.setIsCollected(true);
		
		return productSoldRepo.save(productSoldInDb);
	}

	
	

}
