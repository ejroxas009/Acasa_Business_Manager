package com.acasa.BusinessManager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acasa.BusinessManager.dto.RawMaterialRequestDTO;
import com.acasa.BusinessManager.dto.RestockRequestDTO;
import com.acasa.BusinessManager.model.Ingredient;
import com.acasa.BusinessManager.model.RawMaterial;
import com.acasa.BusinessManager.model.RawMaterialInventory;

@Service
public interface RawMaterialInventoryService {

	List<RawMaterialInventory> getAllRawMaterialInventory();
	RawMaterialInventory getRawMaterialInventoryById(Long rawMaterialInventoryId);
	RawMaterialInventory addRawMaterialInventory(RawMaterialRequestDTO rawMaterialInventory, RawMaterial rawMat);
	void deductInventory(Ingredient ingredient, Double quantity);
	RawMaterialInventory restock(RestockRequestDTO restock);
}
