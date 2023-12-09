package com.acasa.BusinessManager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.acasa.BusinessManager.dto.RawMaterialRequestDTO;
import com.acasa.BusinessManager.model.RawMaterial;

@Service
public interface RawMaterialService {

	List<RawMaterial> getAllRawMaterials();
	RawMaterial getRawMaterialById(Long rawMaterialId);
	RawMaterial addRawMaterial(RawMaterialRequestDTO rawMaterial);
	
}
