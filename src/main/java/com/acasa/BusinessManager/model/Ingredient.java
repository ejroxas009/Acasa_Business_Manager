package com.acasa.BusinessManager.model;

import java.time.LocalDateTime;
import java.util.List;

import com.acasa.BusinessManager.dto.IngredientRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Ingredient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ingredientId;
	@ManyToOne
	private RawMaterial rawMaterial;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "ingredients")
    private List<Product> products;
	private Double quantity;
	private String unit;
	
	private LocalDateTime created;
	private LocalDateTime edited;
	
	
	
	
	
	
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public Ingredient() {
		super();
	}
	public Ingredient(IngredientRequestDTO ingredient) {
		super();
		this.quantity = ingredient.getQuantity();
		this.unit = ingredient.getUnit();
		
	}
	public Long getIngredientId() {
		return ingredientId;
	}
	public void setIngredientId(Long ingredientId) {
		this.ingredientId = ingredientId;
	}
	public RawMaterial getRawMaterial() {
		return rawMaterial;
	}
	public void setRawMaterial(RawMaterial rawMaterial) {
		this.rawMaterial = rawMaterial;
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
