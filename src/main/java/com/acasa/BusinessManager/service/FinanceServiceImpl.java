package com.acasa.BusinessManager.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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

//	@Override
//	public Double getAllSales() {
//		double sum = (double)orderService.getAllOrders()
//				.stream()
//				.mapToDouble(order -> order.getTotalPrice())
//				.sum();
//		
//		
//		
//		BigDecimal bigDecimal = new BigDecimal(sum);
//		bigDecimal = bigDecimal.setScale(2,RoundingMode.HALF_UP);
//		
//		DecimalFormat df = new DecimalFormat("#0.00");
//		return Double.parseDouble(df.format(bigDecimal));
//				
//	}
	
	@Override
	public Double getAllSales() {
	    double sum = orderService.getAllOrders()
	            .stream()
	            .mapToDouble(order -> order.getTotalPrice())
	            .sum();
	    
	    BigDecimal bd = new BigDecimal(sum);
	    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
	    
	    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
	    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
	}

	@Override
	public Double getSalesByProduct(Long productId) {
		double sum = orderService.getAllProductsSold()
				.stream()
				.filter(sold -> sold.getProduct().getProductId().equals(productId))
				.mapToDouble(sold -> sold.getTotalPrice())
				.sum();
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
		    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
				
	}

	@Override
	public Double getAllCollectedSales() {
		double sum = orderService.getAllOrders()
				.stream()
				.filter(order -> order.getIsCollected())
				.mapToDouble(order -> order.getTotalPrice())
				.sum();
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
		    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
	}

	@Override
	public Double getAllUncollectedSales() {
		double sum = orderService.getAllOrders()
				.stream()
				.filter(order -> !order.getIsCollected())
				.mapToDouble(order -> order.getTotalPrice())
				.sum();
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
		    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
	}

	@Override
	public Double getAllEarnings() {
		
		double sum = orderService.getAllOrders()
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
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
		    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
	}

	@Override
	public Double getAllRtsLoses() {
		double sum = orderService.getAllOrders()
				.stream()
				.filter(order -> order.getIsRTS())
				.mapToDouble(order -> order.getRtsLoss())
				.sum();
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
		    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
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

	    double sum =  orders.stream()
	            .flatMap(order -> order.getProductSoldList().stream())
	            .filter(productSold -> productSold.getProduct().getProductId().equals(productId))
	            .mapToDouble(ProductSold::getTotalPrice)
	            .sum();
	    
	    BigDecimal bd = new BigDecimal(sum);
	    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
	    
	    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
	    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
	}


	@Override
	public Double getEarningsByMonth(String month, int year) {
		
		double sum = orderService.getAllOrders()
				.stream()
				.filter(order -> order.getCreated().getMonth().equals(month))
				.filter(order -> order.getCreated().getYear() == year)
				.mapToDouble(order -> order.getTotalPrice())
				.sum();
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
		    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
	}

	@Override
	public Double getEarningsByProductIdByMonth(String month ,int year, Long productId) {
		double sum = orderService.getAllOrders()
				.stream()
				.flatMap(order -> order.getProductSoldList().stream())
				.filter(productSold -> productSold.getProduct().getProductId().equals(productId))
				.filter(productSold -> productSold.getCreated().getMonth().equals(month))
				.filter(productSold -> productSold.getCreated().getYear() == year)
				.mapToDouble(productSold -> productSold.getTotalPrice())
				.sum();
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
		    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
	}

	@Override
	public Double getAllInventoryValuation() {
		double sum = rawMatInventoryService.getAllRawMaterialInventory()
				.stream()
				.mapToDouble(inventory -> inventory.getTotalPrice())
				.sum();
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
		    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
	}
		
	@Override
	public Double getAllInventoryValuationById(Long rawmatId) {
		double sum = rawMatInventoryService.getAllRawMaterialInventory()
				.stream()
				.filter(inventory -> inventory.getRawMaterial().getRawMaterialId().equals(rawmatId))
				.mapToDouble(inventory -> inventory.getTotalPrice())
				.sum();
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
		    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
	}

}
