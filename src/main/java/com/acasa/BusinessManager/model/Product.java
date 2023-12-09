package com.acasa.BusinessManager.model;

import java.time.LocalDateTime;
import java.util.List;

import com.acasa.BusinessManager.dto.ProductRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity	
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	name = "product_ingredient",
	joinColumns = @JoinColumn(name = "product_id"),
	inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
	private List<Ingredient> ingredients;
	@OneToMany(mappedBy ="product")
	private List<ProductSold> productSold;
	private String productName;
	private String productDescription;
//	private List<ProductImage> productImages;
	private Double productPrice;
	private LocalDateTime created;
	private LocalDateTime edited;
	
	
	
	
	
	
	public Product() {
		super();
	}
	public Product(ProductRequestDTO product) {
		super();
		this.productName = product.getProductName();
		this.productDescription = product.getProductDescription();
		this.productPrice = product.getProductPrice();
		
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
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
//	public List<ProductImage> getProductImages() {
//		return productImages;
//	}
//	public void setProductImages(List<ProductImage> productImages) {
//		this.productImages = productImages;
//	}
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
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
