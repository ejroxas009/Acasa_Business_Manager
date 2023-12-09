package com.acasa.BusinessManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acasa.BusinessManager.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
