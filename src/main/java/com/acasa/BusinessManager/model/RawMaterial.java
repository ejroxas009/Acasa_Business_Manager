package com.acasa.BusinessManager.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RawMaterial {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long rawMaterialId;
	private String rawMaterialName;
	private String rawMaterialPrice;
	private LocalDateTime created;
	private LocalDateTime edited;
	
	
	public Long getRaMaterialId() {
		return rawMaterialId;
	}
	public void setRaMaterialId(Long raMaterialId) {
		this.rawMaterialId = raMaterialId;
	}
	public String getRawMaterialName() {
		return rawMaterialName;
	}
	public void setRawMaterialName(String rawMaterialName) {
		this.rawMaterialName = rawMaterialName;
	}
	public String getRawMaterialPrice() {
		return rawMaterialPrice;
	}
	public void setRawMaterialPrice(String rawMaterialPrice) {
		this.rawMaterialPrice = rawMaterialPrice;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getEdited() {
		return edited;
	}
	public void setEdited(LocalDateTime edited) {
		this.edited = edited;
	}
	
	
	
}
