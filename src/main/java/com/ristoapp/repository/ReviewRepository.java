package com.ristoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.ristoapp.models.Review;

public interface ReviewRepository extends CrudRepository<Review, Long> {

}
