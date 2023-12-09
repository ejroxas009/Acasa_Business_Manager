package com.acasa.BusinessManager.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.acasa.BusinessManager.dto.IngredientRequestDTO;
import com.acasa.BusinessManager.model.Ingredient;
import com.acasa.BusinessManager.model.RawMaterial;
import com.acasa.BusinessManager.repository.IngredientRepository;

@Service
public class IngredientServiceImpl implements IngredientService{
	
	private final IngredientRepository ingredientRepo;
	private final RawMaterialService rawMatService;
	
	

	public IngredientServiceImpl(IngredientRepository ingredientRepo, RawMaterialService rawMatService) {
		super();
		this.ingredientRepo = ingredientRepo;
		this.rawMatService = rawMatService;
	}

	@Override
	public List<Ingredient> getAllIngredients() {
		List<Ingredient> ingredients = ingredientRepo.findAll();
		return ingredients;
	}

	@Override
	public Ingredient getIngredientById(Long ingredientId) {
		Ingredient ingredient = ingredientRepo.findById(ingredientId).orElseThrow();
		return ingredient;
	}

	@Override
	public Ingredient addIngredient(IngredientRequestDTO ingredient) {
		Ingredient newIngredient = new Ingredient(ingredient);
		RawMaterial rawMaterial = rawMatService.getRawMaterialById(ingredient.getRawMaterialId());
		newIngredient.setRawMaterial(rawMaterial);
		newIngredient.setCreated(LocalDateTime.now());
		return ingredientRepo.save(newIngredient);
	}

}
