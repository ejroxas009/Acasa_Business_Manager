package com.acasa.BusinessManager.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProductSold {
	@Id	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productSoldId;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	private Double quantity;
	private Boolean isCollected;
	private Double totalPrice;
	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private Order order;
	private LocalDateTime created;
	private LocalDateTime edited;
	public Long getProductSoldId() {
		return productSoldId;
	}
	public void setProductSoldId(Long productSoldId) {
		this.productSoldId = productSoldId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public Boolean getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(Boolean isCollected) {
		this.isCollected = isCollected;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	
}
