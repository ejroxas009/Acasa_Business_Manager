package com.acasa.BusinessManager.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acasa.BusinessManager.dto.RestockRequestDTO;
import com.acasa.BusinessManager.model.RawMaterialInventory;
import com.acasa.BusinessManager.service.RawMaterialInventoryService;

@RestController
@RequestMapping(value = "/api/raw-material-inventory")
public class RawMaterialInventoryController {
	
	private final RawMaterialInventoryService rawMaterialInventoryService;


	
	public RawMaterialInventoryController(RawMaterialInventoryService rawMaterialInventoryService) {
		super();
		this.rawMaterialInventoryService = rawMaterialInventoryService;
	}

	@GetMapping
	public List<RawMaterialInventory> getAllRawMaterialInventory(){
		return rawMaterialInventoryService.getAllRawMaterialInventory();
	}
	
	@GetMapping(value = "/{rawMaterialInventoryId}")
	public RawMaterialInventory getRawMaterialInventoryById(@PathVariable Long rawMaterialInventoryId) {
		return rawMaterialInventoryService.getRawMaterialInventoryById(rawMaterialInventoryId);
	}
	
	@PutMapping(value = "/restock")
	public RawMaterialInventory restock(@RequestBody RestockRequestDTO restock) {
		return rawMaterialInventoryService.restock(restock);
	}
	
	}
