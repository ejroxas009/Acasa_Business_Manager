package com.acasa.BusinessManager.dto;

public class RestockRequestDTO {

	private Long rawMaterialInventoryId;
	private Double quantity;
	
	
	
	public Long getRawMaterialInventoryId() {
		return rawMaterialInventoryId;
	}
	public void setRawMaterialInventoryId(Long rawMaterialInventoryId) {
		this.rawMaterialInventoryId = rawMaterialInventoryId;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	
	
}
