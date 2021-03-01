package com.ristoapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ristoapp.models.Product;	
import com.ristoapp.services.ProductService;

@RestController
@RequestMapping("/api/${app.version}/user/products")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping()
	public ResponseEntity<List<Product>> getProducts(@RequestParam Optional<Long> idCategory) {
		return productService.getProducts(idCategory);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable long id) {
		return productService.getProductById(id);
	}
	
	@PostMapping()
	public ResponseEntity<?> addProduct(@RequestBody Product product) {
		return productService.addProduct(product);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
		return productService.updateProduct(id, product);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable long id) {
		return productService.deleteProduct(id);
	}
}
