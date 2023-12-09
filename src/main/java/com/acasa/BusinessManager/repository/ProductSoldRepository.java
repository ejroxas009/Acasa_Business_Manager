package com.acasa.BusinessManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acasa.BusinessManager.model.ProductSold;

@Repository
public interface ProductSoldRepository extends JpaRepository<ProductSold, Long>{

}
