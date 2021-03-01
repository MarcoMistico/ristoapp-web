package com.ristoapp.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ristoapp.models.Order;

public class OrderServiceImpl implements OrderService {

	@Override
	public ResponseEntity<List<Order>> getOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Order> getOrderById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<?> addOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Order> updateOrder(long id, Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<String> deleteOrder(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
