package com.ristoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.ristoapp.models.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
