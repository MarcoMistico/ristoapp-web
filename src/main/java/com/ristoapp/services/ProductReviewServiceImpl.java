package com.ristoapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ristoapp.models.Product;
import com.ristoapp.models.Review;
import com.ristoapp.repository.ProductRepository;
import com.ristoapp.repository.ReviewRepository;
import com.ristoapp.response.AverageReviewResponse;

@Service
public class ProductReviewServiceImpl implements ProductReviewService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ReviewRepository reviewRepository;

	@Override
	public ResponseEntity<List<Review>> getProductReviews(long idProduct) {
		Optional<Product> product = productRepository.findById(idProduct);

		try {
			if (!product.isPresent())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(product.get().getReviews(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> getAverageProductReviewsValue(long idProduct) {
		Optional<Product> product = productRepository.findById(idProduct);

		try {
			if (!product.isPresent())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(getAverageReviewsNumber(product.get()), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<Review> getProductReviewById(long idProduct, long id) {
		Optional<Product> product = productRepository.findById(idProduct);

		try {
			if (!product.isPresent())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
			Optional<Review> review = reviewRepository.findById(id);
			try {
				if (!review.isPresent())
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
				return new ResponseEntity<>(review.get(), HttpStatus.OK);
			} catch(Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> addProductReview(long idProduct, Review review) {
		Optional<Product> product = productRepository.findById(idProduct);

		try {
			if (!product.isPresent())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			try {
				Review _review = new Review(review.getValue());
				
				Product _product = product.get();
				_product.getReviews().add(_review);
				productRepository.save(_product);
				return new ResponseEntity<>(_review.getId(), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Review> updateProductReview(long idProduct, long id, Review review) {
		Optional<Product> product = productRepository.findById(idProduct);

		try {
			if (!product.isPresent())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			Optional<Review> theReview = reviewRepository.findById(id);
			
			try {
				if (!theReview.isPresent())
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				Review _theReview = theReview.get();
				_theReview.setValue(review.getValue());
				
				reviewRepository.save((_theReview));
				return new ResponseEntity<>(_theReview, HttpStatus.OK);

			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<String> deleteProductReview(long idProduct, long id) {
		Optional<Product> product = productRepository.findById(idProduct);

		try {
			if (!product.isPresent())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			Optional<Review> review = reviewRepository.findById(id);
			
			try {
				if (!review.isPresent())
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				reviewRepository.delete(review.get());
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private AverageReviewResponse getAverageReviewsNumber(Product product) {
		if (product.getReviews().size() == 0) {
			return new AverageReviewResponse(product.getId(), 0, 0.0);
		}
		double averageReviewsNumber = 0;
		int totReviews = 0;
		for (int i = 0; i < product.getReviews().size(); i++) {
			totReviews += product.getReviews().get(i).getValue();
			if (i == product.getReviews().size()-1) {
				averageReviewsNumber = round(totReviews/(i+1), 2);
			}
		}
		return new AverageReviewResponse(product.getId(), totReviews, averageReviewsNumber);
	}
	
	private double round (double value, int precision) {
	    int scale = (int) Math.pow(10, precision);
	    return (double) Math.round(value * scale) / scale;
	}

}
