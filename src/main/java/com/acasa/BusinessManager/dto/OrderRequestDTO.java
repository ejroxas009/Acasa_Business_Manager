package com.acasa.BusinessManager.dto;

import java.util.List;

public class OrderRequestDTO {
	
	private String customerName;
	private List<ProductSoldRequestDTO> productSoldDTO;
	private Double shopeeCommission;
	private Double extraExpense;
	private String extraExpenseDescription;
	private Double extraEarnings;
	private String extraEarningsDescription;
	
	
	
	public List<ProductSoldRequestDTO> getProductSoldDTO() {
		return productSoldDTO;
	}
	public void setProductSoldDTO(List<ProductSoldRequestDTO> productSoldDTO) {
		this.productSoldDTO = productSoldDTO;
	}
	public Double getShopeeCommission() {
		return shopeeCommission;
	}
	public void setShopeeCommission(Double shopeeCommission) {
		this.shopeeCommission = shopeeCommission;
	}
	public Double getExtraExpense() {
		return extraExpense;
	}
	public void setExtraExpense(Double extraExpense) {
		this.extraExpense = extraExpense;
	}
	public String getExtraExpenseDescription() {
		return extraExpenseDescription;
	}
	public void setExtraExpenseDescription(String extraExpenseDescription) {
		this.extraExpenseDescription = extraExpenseDescription;
	}
	public Double getExtraEarnings() {
		return extraEarnings;
	}
	public void setExtraEarnings(Double extraEarnings) {
		this.extraEarnings = extraEarnings;
	}
	public String getExtraEarningsDescription() {
		return extraEarningsDescription;
	}
	public void setExtraEarningsDescription(String extraEarningsDescription) {
		this.extraEarningsDescription = extraEarningsDescription;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	

}
