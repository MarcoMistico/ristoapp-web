package com.ristoapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ristoapp.models.Order;
import com.ristoapp.services.OrderService;

@RestController
@RequestMapping("/api/${app.version}/user/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@GetMapping()
	public ResponseEntity<List<Order>> getOrders() {
		return orderService.getOrders();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable long id) {
		return orderService.getOrderById(id);
	}
	
	@PostMapping()
	public ResponseEntity<?> addOrder(@RequestBody Order order) {
		return orderService.addOrder(order);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable long id, @RequestBody Order order) {
		return orderService.updateOrder(id, order);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteOrder(@PathVariable long id) {
		return orderService.deleteOrder(id);
	}
}
