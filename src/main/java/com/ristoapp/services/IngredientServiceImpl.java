package com.ristoapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ristoapp.models.Ingredient;
import com.ristoapp.repository.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService{
	
	@Autowired
	IngredientRepository repository;

	@Override
	public ResponseEntity<List<Ingredient>> getIngredients() {
		List<Ingredient> ingredients = new ArrayList<>();

		try {
			repository.findAll().forEach(ingredients::add);

			if (ingredients.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(ingredients, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Ingredient> getIngredientById(long id) {
		Optional<Ingredient> ingredient = repository.findById(id);

		try {
			if (!ingredient.isPresent())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);

			return new ResponseEntity<>(ingredient.get(), HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<?> addIngredient(Ingredient ingredient) {
		
		try {
			Ingredient _ingredient = new Ingredient(ingredient.getName(), ingredient.isEditable());
			
			repository.save(_ingredient);
			return new ResponseEntity<>(_ingredient.getId(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Ingredient> updateIngredient(long id, Ingredient ingredient) {
		Optional<Ingredient> theIngredient = repository.findById(id);

		try {
			if (!theIngredient.isPresent())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			Ingredient _theIngredient = theIngredient.get();
			_theIngredient.setName(ingredient.getName());
			_theIngredient.setEditable(ingredient.isEditable());
			
			repository.save((_theIngredient));
			return new ResponseEntity<>(_theIngredient, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> deleteIngredient(long id) {
		Optional<Ingredient> ingredient = repository.findById(id);

		try {
			if (!ingredient.isPresent())
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			repository.delete(ingredient.get());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
