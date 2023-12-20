package com.acasa.BusinessManager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acasa.BusinessManager.dto.RawMaterialRequestDTO;
import com.acasa.BusinessManager.dto.RestockRequestDTO;
import com.acasa.BusinessManager.model.Ingredient;
import com.acasa.BusinessManager.model.RawMaterial;
import com.acasa.BusinessManager.model.RawMaterialInventory;
import com.acasa.BusinessManager.repository.RawMaterialInventoryRepository;

@Service
public class RawMaterialInventoryServiceImpl implements RawMaterialInventoryService {
	
	private final RawMaterialInventoryRepository rawMaterialInventoryRepo;
	
	

	public RawMaterialInventoryServiceImpl(RawMaterialInventoryRepository rawMaterialInventoryRepo) {
		super();
		this.rawMaterialInventoryRepo = rawMaterialInventoryRepo;
	}

	@Override
	public List<RawMaterialInventory> getAllRawMaterialInventory() {
		return rawMaterialInventoryRepo.findAll();
	}

	@Override
	public RawMaterialInventory getRawMaterialInventoryById(Long rawMaterialInventoryId) {
		return rawMaterialInventoryRepo.findById(rawMaterialInventoryId).orElseThrow();
	}

	@Override
	public RawMaterialInventory addRawMaterialInventory(RawMaterialRequestDTO rawMaterialInventory, RawMaterial rawMat) {
		RawMaterialInventory newRawMatInventory = new RawMaterialInventory();
//		RawMaterial rawMat = rawMaterialService.getRawMaterialById(rawMaterialId);
		newRawMatInventory.setRawMaterial(rawMat);
		newRawMatInventory.setQuantity(rawMaterialInventory.getQuantity());
		newRawMatInventory.setUnit(rawMaterialInventory.getUnit());
		newRawMatInventory.setPrice(rawMaterialInventory.getRawMaterialPrice());
		return rawMaterialInventoryRepo.save(newRawMatInventory);
	}

	@Override
	public void deductInventory(Ingredient ingredient, Double quantity) {
		RawMaterialInventory inventoryInDb = rawMaterialInventoryRepo.getInventoryByRawMaterialId(ingredient.getRawMaterial().getRawMaterialId());
		
		inventoryInDb.setQuantity(inventoryInDb.getQuantity() - (quantity*ingredient.getQuantity()));
		rawMaterialInventoryRepo.save(inventoryInDb);
	}

	@Override
	public RawMaterialInventory restock(RestockRequestDTO restock) {
		RawMaterialInventory inventoryInDb = this.getRawMaterialInventoryById(restock.getRawMaterialInventoryId());
		inventoryInDb.setQuantity(inventoryInDb.getQuantity() + restock.getQuantity());
		return rawMaterialInventoryRepo.save(inventoryInDb);
	}
	
	

}
