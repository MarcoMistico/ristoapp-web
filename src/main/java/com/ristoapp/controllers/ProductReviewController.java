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

import com.ristoapp.models.Review;
import com.ristoapp.services.ProductReviewService;

@RestController
@RequestMapping("/api/${app.version}/user/products/{idProduct}/reviews")
public class ProductReviewController {

	@Autowired
	ProductReviewService productReviewService;

	@GetMapping()
	public ResponseEntity<List<Review>> getProducts(@PathVariable long idProduct) {
		return productReviewService.getProductReviews(idProduct);
	}
	
	@GetMapping("/average")
	public ResponseEntity<?> getAverageProductReviewsValue(@PathVariable long idProduct) {
		return productReviewService.getAverageProductReviewsValue(idProduct);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Review> getProductById(@PathVariable long idProduct, @PathVariable long id) {
		return productReviewService.getProductReviewById(idProduct, id);
	}
	
	@PostMapping()
	public ResponseEntity<?> addProduct(@PathVariable long idProduct, @RequestBody Review product) {
		return productReviewService.addProductReview(idProduct, product);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Review> updateProduct(@PathVariable long idProduct, @PathVariable long id, @RequestBody Review product) {
		return productReviewService.updateProductReview(idProduct, id, product);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable long idProduct, @PathVariable long id) {
		return productReviewService.deleteProductReview(idProduct, id);
	}
}
