package com.ristoapp.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ristoapp.models.Product;

public interface ProductService {

	public ResponseEntity<List<Product>> getProducts();
	
	public ResponseEntity<Product> getProductById(long id);
	
	public ResponseEntity<?> addProduct(Product product);
	
	public ResponseEntity<Product> updateProduct(long id, Product product);
	
	public ResponseEntity<String> deleteProduct(long id);
}
