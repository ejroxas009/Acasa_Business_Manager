package com.acasa.BusinessManager.dto;

public class ProductSoldRequestDTO {
	private Long productId;
	private Double quantity;
	

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productSoldId) {
		this.productId = productSoldId;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	
}
