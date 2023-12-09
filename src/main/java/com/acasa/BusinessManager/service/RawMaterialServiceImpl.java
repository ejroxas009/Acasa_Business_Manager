package com.acasa.BusinessManager.service;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.stereotype.Service;

import com.acasa.BusinessManager.dto.RawMaterialRequestDTO;
import com.acasa.BusinessManager.model.RawMaterial;
import com.acasa.BusinessManager.repository.RawMaterialRepository;

@Service
public class RawMaterialServiceImpl implements RawMaterialService {
	
	private final RawMaterialRepository rawMatRepo;
	
	private final RawMaterialInventoryService rawMaterialInventoryService;
	
	

	

	

	public RawMaterialServiceImpl(RawMaterialRepository rawMatRepo,
			RawMaterialInventoryService rawMaterialInventoryService) {
		super();
		this.rawMatRepo = rawMatRepo;
		this.rawMaterialInventoryService = rawMaterialInventoryService;
	}



	@Override
	public List<RawMaterial> getAllRawMaterials() {
		List<RawMaterial> rawMaterials = rawMatRepo.findAll();
		return rawMaterials;
	}

	

	@Override
	public RawMaterial getRawMaterialById(Long rawMaterialId) {
		RawMaterial rawMaterial = rawMatRepo.findById(rawMaterialId).orElseThrow();
		return rawMaterial;
	}

	@Override
	public RawMaterial addRawMaterial(RawMaterialRequestDTO rawMaterial) {
		RawMaterial newRawMaterial = new RawMaterial();
		RawMaterial rawMaterialReturned = new RawMaterial();
		newRawMaterial.setRawMaterialName(rawMaterial.getRawMaterialName());
		newRawMaterial.setCreated(LocalDateTime.now());
		rawMaterialReturned = rawMatRepo.save(newRawMaterial);
		rawMaterialInventoryService.addRawMaterialInventory(rawMaterial, rawMaterialReturned);
		
		return rawMaterialReturned;
		
	}

}
