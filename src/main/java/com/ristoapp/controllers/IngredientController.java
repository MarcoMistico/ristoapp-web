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

import com.ristoapp.models.Ingredient;
import com.ristoapp.services.IngredientService;

@RestController
@RequestMapping("/api/${app.version}/editor/ingredients")
public class IngredientController {

	@Autowired
	IngredientService ingredientService;
	
	@GetMapping()
	public ResponseEntity<List<Ingredient>> getIngredients() {
		return ingredientService.getIngredients();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Ingredient> getIngredientById(@PathVariable long id) {
		return ingredientService.getIngredientById(id);
	}
	
	@PostMapping()
	public ResponseEntity<?> addIngredient(@RequestBody Ingredient ingredient) {
		return ingredientService.addIngredient(ingredient);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Ingredient> updateIngredient(@PathVariable long id, @RequestBody Ingredient ingredient) {
		return ingredientService.updateIngredient(id, ingredient);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteIngredient(@PathVariable long id) {
		return ingredientService.deleteIngredient(id);
	}
}
