package com.ristoapp.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ristoapp.enums.OrderStatusEnum;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "table_number")
	private int tableNumber;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@Column(name = "amount")
	private double amount;
	
	@Enumerated(EnumType.ORDINAL)
    private OrderStatusEnum status;

	public Order() {
	}

	public Order(int tableNumber, Date orderDate, double amount, OrderStatusEnum status) {
		this.tableNumber = tableNumber;
		this.orderDate = orderDate;
		this.amount = amount;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public OrderStatusEnum getStatus() {
		return status;
	}

	public void setStatus(OrderStatusEnum status) {
		this.status = status;
	}
	
}
