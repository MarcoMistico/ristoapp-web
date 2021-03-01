package com.ristoapp.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ristoapp.models.Order;

public interface OrderService {

	ResponseEntity<List<Order>> getOrders();

	ResponseEntity<Order> getOrderById(long id);

	ResponseEntity<?> addOrder(Order order);

	ResponseEntity<Order> updateOrder(long id, Order order);

	ResponseEntity<String> deleteOrder(long id);

}
