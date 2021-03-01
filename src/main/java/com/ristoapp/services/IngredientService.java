package com.ristoapp.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.ristoapp.models.Ingredient;

public interface IngredientService {

	public ResponseEntity<List<Ingredient>> getIngredients();

	public ResponseEntity<Ingredient> getIngredientById(long id);

	public ResponseEntity<?> addIngredient(Ingredient ingredient);

	public ResponseEntity<Ingredient> updateIngredient(long id, Ingredient ingredient);

	public ResponseEntity<String> deleteIngredient(long id);

}
