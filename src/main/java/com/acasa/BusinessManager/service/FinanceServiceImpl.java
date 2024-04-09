package com.acasa.BusinessManager.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		double sum = (double)orderService.getAllOrders()
				.stream()
				.mapToDouble(order -> order.getTotalPrice())
				.sum();
		
		
		
		BigDecimal bigDecimal = new BigDecimal(sum);
		bigDecimal = bigDecimal.setScale(2,RoundingMode.HALF_UP);
		
		DecimalFormat df = new DecimalFormat("#0.00");
		return Double.parseDouble(df.format(bigDecimal));
				
	}
	
//	@Override
//	public Double getAllSales() {
//	    double sum = orderService.getAllOrders()
//	            .stream()
//	            .mapToDouble(order -> order.getTotalPrice())
//	            .sum();
//	    
//	    BigDecimal bd = new BigDecimal(sum);
//	    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
//	    
//	    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
//	    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
//	}
	
	

	@Override
	public Double getSalesByProduct(Long productId) {
		double sum = orderService.getAllProductsSold()
				.stream()
				.filter(sold -> sold.getProduct().getProductId().equals(productId))
				.mapToDouble(sold -> sold.getTotalPrice())
				.sum();
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#,##0.00");
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
	public Double getEarningsByMonth(int month, int year) {
		
		double sum = orderService.getAllOrders()
				.stream()
				.filter(order -> order.getCreated().getMonthValue() == month)
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

	

	@Override
	public Double getCurrentMontsSales() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		double sum = (double)orderService.getAllOrders()
				.stream()
				.filter(order -> order.getCreated() != null)
				.filter(order -> order.getCreated().getMonth().equals(currentDateTime.getMonth()))
				.filter(order -> order.getCreated().getYear() == currentDateTime.getYear())
				.mapToDouble(order -> order.getTotalPrice())
				.sum();
		
		
		
		BigDecimal bigDecimal = new BigDecimal(sum);
		bigDecimal = bigDecimal.setScale(2,RoundingMode.HALF_UP);
		
		DecimalFormat df = new DecimalFormat("#0.00");
		return Double.parseDouble(df.format(bigDecimal));
	}

	@Override
	public Double getCurrentMonthsEarnings() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		Double currentMonthsEarnings = this.getEarningsByMonth(currentDateTime.getMonthValue(), currentDateTime.getYear());
		return currentMonthsEarnings;
	}

	@Override
	public Double getCurrentMonthsUncollectedSales() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		double sum = orderService.getAllOrders()
				.stream()
				.filter(order -> order.getCreated() != null)
				.filter(order -> order.getCreated().getMonthValue() == currentDateTime.getMonthValue())
				.filter(order -> order.getCreated().getYear() == currentDateTime.getYear())
				.filter(order -> !order.getIsCollected())
				.mapToDouble(order -> order.getTotalPrice())
				.sum();
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
		    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
	}

	@Override
	public Double getCurrentMonthsRTS() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		double sum = orderService.getAllOrders()
				.stream()
				.filter(order -> order.getIsRTS())
				.filter(order -> order.getCreated() != null)
				.filter(order -> order.getCreated().getMonthValue() == currentDateTime.getMonthValue())
				.filter(order -> order.getCreated().getYear() == currentDateTime.getYear())
				.mapToDouble(order -> order.getRtsLoss())
				.sum();
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
		    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
		
	}

	@Override
	public Double getCurrentMonthsExpenses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double getPreviousMonthsSales() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		int previousMonth;
		int year;
		
		if(currentDateTime.getMonthValue() == 1) {
			previousMonth = 12;
			year = currentDateTime.getYear() -1;
		}else {
			previousMonth = currentDateTime.getMonthValue() -1;
			year = currentDateTime.getYear();
		}
		
		double sum = (double)orderService.getAllOrders()
				.stream()
				.filter(order -> order.getCreated() != null)
				.filter(order -> order.getCreated().getMonthValue() == previousMonth)
				.filter(order -> order.getCreated().getYear() == year)
				.mapToDouble(order -> order.getTotalPrice())
				.sum();
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
		    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
	}

	@Override
	public Double getPreviousMonthsEarnings() {
		int previousMonth = this.getPreviousMonthValue();
		int year = this.getPreviousOrCurrentYear();
		Double previousMonthsEarnings = this.getEarningsByMonth(previousMonth, year);
		
		return previousMonthsEarnings;
	}

	@Override
	public Double getPreviousMonthsUncollectedSales() {
		
		int previousMonth = this.getPreviousMonthValue();
		int year = this.getPreviousOrCurrentYear();
		
		double sum = orderService.getAllOrders()
				.stream()
				.filter(order -> order.getCreated() != null)
				.filter(order -> order.getCreated().getMonthValue() == previousMonth)
				.filter(order -> order.getCreated().getYear() == year)
				.filter(order -> !order.getIsCollected())
				.mapToDouble(order -> order.getTotalPrice())
				.sum();
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
		    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
	}

	@Override
	public Double getPreviousMonthsRTS() {
		int previousMonth = this.getPreviousMonthValue();
		int year = this.getPreviousOrCurrentYear();
		
		double sum = orderService.getAllOrders()
				.stream()
				.filter(order -> order.getIsRTS())
				.filter(order -> order.getCreated() != null)
				.filter(order -> order.getCreated().getMonthValue() == previousMonth)
				.filter(order -> order.getCreated().getYear() == year)
				.mapToDouble(order -> order.getRtsLoss())
				.sum();
		
		 BigDecimal bd = new BigDecimal(sum);
		    bd = bd.setScale(2, RoundingMode.HALF_UP); // Set scale to 2 decimal places, rounding mode HALF_UP
		    
		    DecimalFormat df = new DecimalFormat("#0.00"); // Format to two decimal places
		    return Double.parseDouble(df.format(bd)); // Convert formatted BigDecimal back to double
	}

	@Override
	public Double getPreviousMonthsExpenses() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	-----------------------------------------Percentage Change --------------------------------------------
	
	@Override
	public String getCurrentMonthsSalesPercentageChange() {
		Double currentMonthsSales = this.getCurrentMontsSales();
		Double previousMonthsSales = this.getPreviousMonthsSales();
		
		if(previousMonthsSales == 0.0) {
			return "No Sales";
		}
		
		Double salesPercentageChange = ((currentMonthsSales-previousMonthsSales)/previousMonthsSales)*100;
		return salesPercentageChange.toString() + "%";
	}

	@Override
	public String getTotalEarningsPercentageChange() {
		Double currentMonthsEarnings = this.getCurrentMonthsEarnings();
		Double previousMonthsEarnings = this.getPreviousMonthsEarnings();
		
		if(previousMonthsEarnings == 0.0) {
			return "No earnings";
		}
		
		Double currentMonthsEarningsPercentage = ((currentMonthsEarnings - previousMonthsEarnings)/previousMonthsEarnings)*100;
		return currentMonthsEarningsPercentage.toString() + "%";
	}

	
	@Override
	public String getCurrentMonthsRTSPercentageChange() {
		Double currentMonthsRTS = this.getCurrentMonthsRTS();
		Double previousMonthsRTS = this.getPreviousMonthsRTS();

		Double RTSPercentageChange;
		
		System.out.println("Current Month's RTS: " + currentMonthsRTS);
		System.out.println("Previous Month's RTS: " + previousMonthsRTS);
		if(previousMonthsRTS == 0) {
			return "No RTS";
		}
		RTSPercentageChange = ((currentMonthsRTS - previousMonthsRTS)/previousMonthsRTS)*100;
		return RTSPercentageChange.toString() + "%";
	}

	@Override
	public Double getTotalExpensesPercentageChange() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrentMonthsUncollectedSalesPercentageChange() {
		Double currentMonthUncollectedSales = this.getCurrentMonthsUncollectedSales();
		Double previousMonthUncollectedSales = this.getPreviousMonthsUncollectedSales();
		
		Double uncollectedSalesPercentageChange;
		
		if(previousMonthUncollectedSales == 0) {
			return "No Uncollected Sales";
		}
		
		uncollectedSalesPercentageChange = ((currentMonthUncollectedSales - previousMonthUncollectedSales)/previousMonthUncollectedSales)*100;
		return uncollectedSalesPercentageChange.toString() + "%";
	}

	
//	--------------------------------------------Utils --------------------------
	
	private int getPreviousMonthValue() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		int previousMonth;
		if(currentDateTime.getMonthValue() ==1 ) {
			previousMonth = 12;
		}else {
			previousMonth = currentDateTime.getMonthValue() -1;
		}
		
		return previousMonth;
	}
	
	
	private int getPreviousOrCurrentYear() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		int year;
		
		if(currentDateTime.getMonthValue() ==1) {
			year = currentDateTime.getYear() -1;
		}else {
			year = currentDateTime.getYear();
		}
		
		return year;
		
	}
	
	
//	-----------------------------------------------------------------------------------------

	@Override
	public List<Map<String, Object>> getYearlySales() {
		List<Map<String, Object>> data = new ArrayList<>();
		LocalDateTime currentDateTime = LocalDateTime.now();
		String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		
		for(int i = 1; i<= 12; i++) {
			int month = i;
			Map<String, Object> monthlyData = new HashMap<>();
			Double sum = orderService.getAllOrders()
					.stream()
					.filter(order->order.getCreated() != null)
					.filter(order -> order.getCreated().getMonthValue() == month)
					.filter(order -> order.getCreated().getYear() == currentDateTime.getYear())
					.filter(order->order.getIsCollected())
					.mapToDouble(order-> order.getTotalPrice())
					.sum();
			
			monthlyData.put("month", months[month -1]);
			monthlyData.put("sales", sum);
			
			data.add(monthlyData);
			
		}
		
		return data;
	}
}
