package com.acasa.BusinessManager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acasa.BusinessManager.dto.IngredientRequestDTO;
import com.acasa.BusinessManager.model.Ingredient;

@Service
public interface IngredientService {

	List<Ingredient> getAllIngredients();
	Ingredient getIngredientById(Long ingredientId);
	Ingredient addIngredient(IngredientRequestDTO ingredient);
}
