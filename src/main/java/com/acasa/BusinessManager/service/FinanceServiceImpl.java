package com.acasa.BusinessManager.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acasa.BusinessManager.model.Order;
import com.acasa.BusinessManager.model.ProductSold;

@Service
public class FinanceServiceImpl implements FinanceService {
	
	private final OrderService orderService;
	
	private final RawMaterialInventoryService rawMatInventoryService;
	

	

	public FinanceServiceImpl(OrderService orderService, RawMaterialInventoryService rawMatInventoryService) {
		super();
		this.orderService = orderService;
		this.rawMatInventoryService = rawMatInventoryService;
	}

	@Override
	public Double getAllSales() {
		return orderService.getAllOrders()
				.stream()
				.mapToDouble(order -> order.getTotalPrice())
				.sum();
				
	}

	@Override
	public Double getSalesByProduct(Long productId) {
		return orderService.getAllProductsSold()
				.stream()
				.filter(sold -> sold.getProduct().getProductId().equals(productId))
				.mapToDouble(sold -> sold.getTotalPrice())
				.sum();
				
	}

	@Override
	public Double getAllCollectedSales() {
		return orderService.getAllOrders()
				.stream()
				.filter(order -> order.getIsCollected())
				.mapToDouble(order -> order.getTotalPrice())
				.sum();
	}

	@Override
	public Double getAllUncollectedSales() {
		return orderService.getAllOrders()
				.stream()
				.filter(order -> !order.getIsCollected())
				.mapToDouble(order -> order.getTotalPrice())
				.sum();
	}

	@Override
	public Double getAllEarnings() {
		
		return orderService.getAllOrders()
				.stream()
				.filter(order-> order.getIsCollected() && !order.getIsRTS())
				.mapToDouble(order -> {
	                double totalPrice = order.getTotalPrice();
	                double extraExpense = Optional.ofNullable(order.getExtraExpense()).orElse(0.0);
	                double shopeeCommission = Optional.ofNullable(order.getShopeeCommission()).orElse(0.0);
	                double shippingFee = Optional.ofNullable(order.getRtsLoss()).orElse(0.0);

	                return totalPrice - (extraExpense + shopeeCommission + shippingFee);
	            })
				.sum();
	}

	@Override
	public Double getAllRtsLoses() {
		return orderService.getAllOrders()
				.stream()
				.filter(order -> order.getIsRTS())
				.mapToDouble(order -> order.getRtsLoss())
				.sum();
	}

//	@Override
//	public Double getEarningsByProductId(Long productId) {
//		List<Order> orders = orderService.getAllOrders();
//		Double earnings = 0.0;
//		
//		for(Order order : orders) {
//			List<ProductSold> productSoldList = new ArrayList<>();
//			productSoldList = order.getProductSoldList();
//			for(ProductSold productSold : productSoldList) {
//				if(productSold.getProduct().getProductId().equals(productId)) {
//					earnings += productSold.getTotalPrice();
//				}
//			}
//			
//		}
//		return earnings;
//	}
	
	@Override
	public Double getEarningsByProductId(Long productId) {
	    List<Order> orders = orderService.getAllOrders();

	    return orders.stream()
	            .flatMap(order -> order.getProductSoldList().stream())
	            .filter(productSold -> productSold.getProduct().getProductId().equals(productId))
	            .mapToDouble(ProductSold::getTotalPrice)
	            .sum();
	}


	@Override
	public Double getEarningsByMonth(String month, int year) {
		
		return orderService.getAllOrders()
				.stream()
				.filter(order -> order.getCreated().getMonth().equals(month))
				.filter(order -> order.getCreated().getYear() == year)
				.mapToDouble(order -> order.getTotalPrice())
				.sum();
	}

	@Override
	public Double getEarningsByProductIdByMonth(String month ,int year, Long productId) {
		return orderService.getAllOrders()
				.stream()
				.flatMap(order -> order.getProductSoldList().stream())
				.filter(productSold -> productSold.getProduct().getProductId().equals(productId))
				.filter(productSold -> productSold.getCreated().getMonth().equals(month))
				.filter(productSold -> productSold.getCreated().getYear() == year)
				.mapToDouble(productSold -> productSold.getTotalPrice())
				.sum();
	}

	@Override
	public Double getAllInventoryValuation() {
		return rawMatInventoryService.getAllRawMaterialInventory()
				.stream()
				.mapToDouble(inventory -> inventory.getTotalPrice())
				.sum();
	}
		
	@Override
	public Double getAllInventoryValuationById(Long rawmatId) {
		return rawMatInventoryService.getAllRawMaterialInventory()
				.stream()
				.filter(inventory -> inventory.getRawMaterial().getRawMaterialId().equals(rawmatId))
				.mapToDouble(inventory -> inventory.getTotalPrice())
				.sum();
	}

}
