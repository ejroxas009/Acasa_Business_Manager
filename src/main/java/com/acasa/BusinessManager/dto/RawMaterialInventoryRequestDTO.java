package com.acasa.BusinessManager.dto;

public class RawMaterialInventoryRequestDTO {

	private Long rawMaterialId;
	private Double quantity;
	private String unit;
	
	
	
	public Long getRawMaterialId() {
		return rawMaterialId;
	}
	public void setRawMaterialId(Long rawMaterialId) {
		this.rawMaterialId = rawMaterialId;
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
