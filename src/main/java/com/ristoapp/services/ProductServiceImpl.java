package com.ristoapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ristoapp.models.Product;
import com.ristoapp.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository repository;

	@Override
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> products = new ArrayList<>();

		try {
			repository.findAll().forEach(products::add);

			if (products.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Product> getProductById(long id) {
		Optional<Product> product = repository.findById(id);

		try {
			if (!product.isPresent())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(product.get(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> addProduct(Product product) {
		
		try {
			Product _product = new Product(product.getName(), product.getDescription(), product.getCategory(), product.getPriceAmount(),
					product.getIngredients(), product.getReviews(), product.getPicByte());
			
			repository.save(_product);
			return new ResponseEntity<>(_product.getId(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Product> updateProduct(long id, Product product) {
		Optional<Product> theProduct = repository.findById(id);

		try {
			if (!theProduct.isPresent())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			Product _theProduct = theProduct.get();
			_theProduct.setName(product.getName());
			_theProduct.setDescription(product.getDescription());
			_theProduct.setCategory(product.getCategory());
			_theProduct.setPriceAmount(product.getPriceAmount());
			_theProduct.setIngredients(product.getIngredients());
			_theProduct.setReviews(product.getReviews());
			_theProduct.setPicByte(product.getPicByte());
			
			repository.save((_theProduct));
			return new ResponseEntity<>(_theProduct, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> deleteProduct(long id) {
		Optional<Product> product = repository.findById(id);

		try {
			if (!product.isPresent())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			repository.delete(product.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
