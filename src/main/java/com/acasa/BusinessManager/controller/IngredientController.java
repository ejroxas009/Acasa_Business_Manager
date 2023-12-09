package com.acasa.BusinessManager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acasa.BusinessManager.dto.IngredientRequestDTO;
import com.acasa.BusinessManager.model.Ingredient;
import com.acasa.BusinessManager.service.IngredientService;

@RestController
@RequestMapping(value = "/api/ingredient")
public class IngredientController {
	
	private final IngredientService ingredientService;
	
	

	public IngredientController(IngredientService ingredientService) {
		super();
		this.ingredientService = ingredientService;
	}



	@GetMapping
	public List<Ingredient> getAllIngredients(){
		return ingredientService.getAllIngredients();
	}
	
	@GetMapping(value = "/{ingredintId}")
	public Ingredient getIngredientById(@PathVariable Long ingredientId) {
		return ingredientService.getIngredientById(ingredientId);
	}
	
	@PostMapping
	public Ingredient addIngredient(@RequestBody IngredientRequestDTO ingredient) {
		return ingredientService.addIngredient(ingredient);
	}
}
