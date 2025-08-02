package com.example.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventoryservice.entity.Inventory;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    // Custom query to find stock by the product's ID
    Optional<Inventory> findByProductId(Long productId);
}