package com.ristoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.ristoapp.models.ProductCategory;


public interface ProductCategoryRepository extends CrudRepository<ProductCategory, Long> {

	ProductCategory findById(int id);
}
