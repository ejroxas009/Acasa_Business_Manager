package com.acasa.BusinessManager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acasa.BusinessManager.dto.RawMaterialRequestDTO;
import com.acasa.BusinessManager.model.RawMaterial;
import com.acasa.BusinessManager.service.RawMaterialService;

@RestController
@RequestMapping(value = "/api/raw-material")
public class RawMaterialController {

	
	private final RawMaterialService rawMatService;
	
	
	
	
	public RawMaterialController(RawMaterialService rawMatService) {
		super();
		this.rawMatService = rawMatService;
	}




	@GetMapping
	public List<RawMaterial> getAllRawMaterials(){
		return rawMatService.getAllRawMaterials();
	}
	
	@GetMapping(value = "/{rawMatId}")
	public RawMaterial getRawMaterialById(@PathVariable Long rawMatId) {
		return rawMatService.getRawMaterialById(rawMatId);
	}
	
	
	@PostMapping
	public RawMaterial addRawMaterial(@RequestBody RawMaterialRequestDTO rawMaterial) {
		return rawMatService.addRawMaterial(rawMaterial);
	}
}
