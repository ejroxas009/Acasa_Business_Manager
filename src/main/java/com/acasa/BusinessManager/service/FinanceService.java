package com.acasa.BusinessManager.service;

import org.springframework.stereotype.Service;

@Service
public interface FinanceService {

	Double getAllSales();
	Double getSalesByProduct(Long productId);
	Double getAllCollectedSales();
	Double getAllUncollectedSales();
	
	
	Double getAllEarnings();
	Double getAllRtsLoses();
	Double getEarningsByProductId(Long productId);
	Double getEarningsByMonth(String month, int year);
	Double getEarningsByProductIdByMonth(String month, int year, Long productId);
	Double getAllInventoryValuation();
	Double getAllInventoryValuationById(Long inventoryId);
	
	
}
