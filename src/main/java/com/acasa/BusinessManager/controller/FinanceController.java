package com.acasa.BusinessManager.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acasa.BusinessManager.service.FinanceService;

@RestController
@RequestMapping(value = "/api/finance/")
@CrossOrigin(origins = "*")
public class FinanceController {
	
	private final FinanceService financeService;
	
	
	
	public FinanceController(FinanceService financeService) {
		super();
		this.financeService = financeService;
	}



	@GetMapping(value = "sales/all")
	public Double getAllSales() {
		return financeService.getAllSales();
	}
	
	@GetMapping(value = "sales/by-product/{productId}")
	public Double getAllSalesByProductId(@PathVariable Long productId) {
		return financeService.getSalesByProduct(productId);
	}
	
	@GetMapping(value = "sales/collected")
	public Double getAllCollectedSales() {
		return financeService.getAllCollectedSales();
	}
	
	@GetMapping(value = "sales/uncollected")
	public Double getAllUncollectedSales() {
		return financeService.getAllUncollectedSales();
		
	}
	
	@GetMapping(value = "earnings/all")
	public Double getAllEarnings() {
		return financeService.getAllEarnings();
	}
	
	
	@GetMapping(value = "rts")
	public Double getAllRtsLoses() {
		return financeService.getAllRtsLoses();
	}
	
	@GetMapping(value ="earnings/{productId}")
	public Double getAllEarningsByProductId(@PathVariable Long productId) {
		return financeService.getEarningsByProductId(productId);
	
	}
	
	@GetMapping(value ="earnings-by-month/{month}/{year}")
	public Double getEarningsByMonth(@PathVariable String month, @PathVariable int year) {
		return financeService.getEarningsByMonth(month, year);
	}
	
	@GetMapping(value ="earnings-by-month-by-product/{month}/{year}/{productId}")
	public Double getEarningsByProductIdByMonth(@PathVariable String month,@PathVariable int year,@PathVariable Long productId) {
		return financeService.getEarningsByProductIdByMonth(month, year, productId);
	}
	
	
}
