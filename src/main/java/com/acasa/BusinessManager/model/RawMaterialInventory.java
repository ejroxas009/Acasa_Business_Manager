package com.acasa.BusinessManager.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;

@Entity
public class RawMaterialInventory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rawMaterialInventoryId;
	@OneToOne
	private RawMaterial rawMaterial;
	private Double quantity;
	private String unit;
	private Double price;
	@Transient
	private Double totalPrice;
	
	public Long getRawMaterialInventoryId() {
		return rawMaterialInventoryId;
	}
	public void setRawMaterialInventoryId(Long rawMaterialInventoryId) {
		this.rawMaterialInventoryId = rawMaterialInventoryId;
	}
//	public RawMaterial getRawMaterial() {
//		return rawMaterial;
//	}
//	public void setRawMaterial(RawMaterial rawMaterial) {
//		this.rawMaterial = rawMaterial;
//	}
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
	public RawMaterial getRawMaterial() {
		return rawMaterial;
	}
	public void setRawMaterial(RawMaterial rawMaterial) {
		this.rawMaterial = rawMaterial;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getTotalPrice() {
		return this.getPrice() * this.getQuantity();
	}
	
	
	
	
}
