package com.acasa.BusinessManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acasa.BusinessManager.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

}
