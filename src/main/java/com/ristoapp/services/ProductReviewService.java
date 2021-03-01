package com.ristoapp.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ristoapp.models.Review;

public interface ProductReviewService {

	public ResponseEntity<List<Review>> getProductReviews(long idProduct);
	
	public ResponseEntity<?> getAverageProductReviewsValue(long idProduct);
	
	public ResponseEntity<Review> getProductReviewById(long idProduct, long id);
	
	public ResponseEntity<?> addProductReview(long idProduct, Review review);
	
	public ResponseEntity<Review> updateProductReview(long idProduct, long id, Review review);
	
	public ResponseEntity<String> deleteProductReview(long idProduct, long id);
	
}
