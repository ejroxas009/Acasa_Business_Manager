package com.acasa.BusinessManager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acasa.BusinessManager.dto.OrderRequestDTO;
import com.acasa.BusinessManager.dto.RtsRequestDTO;
import com.acasa.BusinessManager.model.Order;
import com.acasa.BusinessManager.service.OrderService;

@RestController
@RequestMapping(value = "/api/order")
@CrossOrigin(origins = "*")
public class OrderController {
	
	private final OrderService orderService;
	
	
	
	public OrderController(OrderService orderService) {
		super();
		this.orderService = orderService;
	}



	@GetMapping
	public List<Order> getAllOrders(){
		return orderService.getAllOrders();
	}

	
	@GetMapping(value = "/{orderId}")
	public Order getOrderById(@PathVariable Long orderId) {
		return orderService.getOrderById(orderId);
	}
	
	@PostMapping
	public Order addOrder(@RequestBody OrderRequestDTO orderRequest) {
		return orderService.addOrder(orderRequest);
	}
	
	@PutMapping(value = "/collect/{orderId}")
	public Order collectOrder(@PathVariable Long orderId) {
		return orderService.collectOrder(orderId);
	}
	
	@PutMapping(value = "/rts")
	public Order markAsRts(@RequestBody RtsRequestDTO rtsRequest) {
		return orderService.markAsRts(rtsRequest);
	}
	
	@GetMapping(value ="/collected")
	public List<Order> getAllCollectedOrders(){
		return orderService.getAllCollectedOrders();
	}
	
	@GetMapping(value = "/uncollected")
	public List<Order> getAllUncollectedOrders(){
		return orderService.getAllUncollectedOrders();
	}
	
	@GetMapping(value = "/rts")
	public List<Order> getAllRts(){
		return orderService.getAllRts();
	}
}
