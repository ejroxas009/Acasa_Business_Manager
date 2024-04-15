package com.acasa.BusinessManager.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.acasa.BusinessManager.model.Product;

@Service
public interface FinanceService {

	Double getAllSales();
	Double getSalesByProduct(Long productId);
	Double getAllCollectedSales();
	Double getAllUncollectedSales();
	
	
	Double getAllEarnings();
	Double getAllRtsLoses();
	Double getEarningsByProductId(Long productId);
	Double getEarningsByMonth(int month, int year);
	Double getEarningsByProductIdByMonth(String month, int year, Long productId);
	Double getAllInventoryValuation();
	Double getAllInventoryValuationById(Long inventoryId);
	
	String getCurrentMonthsSalesPercentageChange();
	String getTotalEarningsPercentageChange();
	String getCurrentMonthsUncollectedSalesPercentageChange();
	String getCurrentMonthsRTSPercentageChange();
	Double getTotalExpensesPercentageChange();
	
	
	Double getCurrentMontsSales();
	Double getCurrentMonthsEarnings();
	Double getCurrentMonthsUncollectedSales();
	Double getCurrentMonthsRTS();
	Double getCurrentMonthsExpenses();
	
	Double getPreviousMonthsSales();
	Double getPreviousMonthsEarnings();
	Double getPreviousMonthsUncollectedSales();
	Double getPreviousMonthsRTS();
	Double getPreviousMonthsExpenses();
	
	List<Map<String, Object>> getYearlySales();
	
	List<Long> getOrdersProductId();
	List<Map<String, Object>> getTopSellingProducts();
	
	
}
