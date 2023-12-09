package com.acasa.BusinessManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.acasa.BusinessManager.model.RawMaterialInventory;

@Repository
public interface RawMaterialInventoryRepository extends JpaRepository<RawMaterialInventory, Long> {

	@Query("SELECT inventory from RawMaterialInventory inventory WHERE inventory.rawMaterial.rawMaterialId = :rawMaterialId")
	RawMaterialInventory getInventoryByRawMaterialId(@Param("rawMaterialId") Long rawMaterialId);
}
