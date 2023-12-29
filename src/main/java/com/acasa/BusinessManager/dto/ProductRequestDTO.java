package com.acasa.BusinessManager.dto;

import java.util.List;

public class ProductRequestDTO {
	private String productName;
	private String productDescription;
	private List<String> ingredientsIdList;
	private List<IngredientRequestDTO> ingredientRequest;
	private Double productPrice;
	
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public List<String> getIngredientsIdList() {
		return ingredientsIdList;
	}
	public void setIngredientsIdList(List<String> ingredientsIdList) {
		this.ingredientsIdList = ingredientsIdList;
	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	public List<IngredientRequestDTO> getIngredientRequest() {
		return ingredientRequest;
	}
	public void setIngredientRequest(List<IngredientRequestDTO> ingredientRequest) {
		this.ingredientRequest = ingredientRequest;
	}
	
	
	
}
