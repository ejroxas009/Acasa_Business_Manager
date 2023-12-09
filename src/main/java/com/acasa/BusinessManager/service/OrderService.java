package com.acasa.BusinessManager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acasa.BusinessManager.dto.OrderRequestDTO;
import com.acasa.BusinessManager.dto.ProductSoldRequestDTO;
import com.acasa.BusinessManager.dto.RtsRequestDTO;
import com.acasa.BusinessManager.model.Order;
import com.acasa.BusinessManager.model.ProductSold;

@Service
public interface OrderService {
	List<Order> getAllOrders();
	Order getOrderById(Long orderId);
	Order addOrder(OrderRequestDTO orderRequest);
	Order collectOrder(Long orderId);
	Order markAsRts(RtsRequestDTO rtsRequest);
	List<Order> getAllCollectedOrders();
	List<Order> getAllUncollectedOrders();
	List<Order> getAllRts();
	
	//------------------------------------------------------------------
	List<ProductSold> getAllProductsSold();
	ProductSold getProductSoldId(Long productSoldId);
	ProductSold addProductSold(ProductSoldRequestDTO productSold, Long orderId);
	ProductSold collectIncome(Long productSoldId);
}
