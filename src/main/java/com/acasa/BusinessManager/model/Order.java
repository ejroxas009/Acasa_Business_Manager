package com.acasa.BusinessManager.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "my_order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	
	private String customerName;
	@OneToMany(mappedBy="order")
//	@Transient
	private List<ProductSold> productSoldList;
	private Double totalPrice;
	private Double shopeeCommission;
	private Double rtsLoss;
	private Double extraExpense;
	private String extraExpenseDescription;
	private Double extraEarnings;
	private String extraEarningsDescription;
	private Boolean isCollected;
	private Boolean isRTS;
	private LocalDateTime created;
	private LocalDateTime edited;
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public List<ProductSold> getProductSoldList() {
		return productSoldList;
	}
	public void setProductSoldList(List<ProductSold> productSoldList) {
		this.productSoldList = productSoldList;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getShopeeCommission() {
		return shopeeCommission;
	}
	public void setShopeeCommission(Double shopeeCommission) {
		this.shopeeCommission = shopeeCommission;
	}
	public Double getExtraExpense() {
		return extraExpense;
	}
	public void setExtraExpense(Double extraExpense) {
		this.extraExpense = extraExpense;
	}
	public String getExtraExpenseDescription() {
		return extraExpenseDescription;
	}
	public void setExtraExpenseDescription(String extraExpenseDescription) {
		this.extraExpenseDescription = extraExpenseDescription;
	}
	public Double getExtraEarnings() {
		return extraEarnings;
	}
	public void setExtraEarnings(Double extraEarnings) {
		this.extraEarnings = extraEarnings;
	}
	public String getExtraEarningsDescription() {
		return extraEarningsDescription;
	}
	public void setExtraEarningsDescription(String extraEarningsDescription) {
		this.extraEarningsDescription = extraEarningsDescription;
	}
	public Boolean getIsCollected() {
		return isCollected;
	}
	public void setIsCollected(Boolean isCollected) {
		this.isCollected = isCollected;
	}
	public Boolean getIsRTS() {
		return isRTS;
	}
	public void setIsRTS(Boolean isRTS) {
		this.isRTS = isRTS;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Double getRtsLoss() {
		return rtsLoss;
	}
	public void setRtsLoss(Double shippingFee) {
		this.rtsLoss = shippingFee;
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
