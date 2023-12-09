package com.acasa.BusinessManager.dto;

public class RawMaterialRequestDTO {
	private String rawMaterialName;
	private Double rawMaterialPrice;
	private Double quantity;
	private String unit;
	
	
	public String getRawMaterialName() {
		return rawMaterialName;
	}
	public void setRawMaterialName(String rawMaterialName) {
		this.rawMaterialName = rawMaterialName;
	}
	public Double getRawMaterialPrice() {
		return rawMaterialPrice;
	}
	public void setRawMaterialPrice(Double rawMaterialPrice) {
		this.rawMaterialPrice = rawMaterialPrice;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	
}
