package com.ristoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.ristoapp.models.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
