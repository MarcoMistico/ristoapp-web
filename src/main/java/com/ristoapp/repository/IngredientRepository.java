package com.ristoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.ristoapp.models.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

}
