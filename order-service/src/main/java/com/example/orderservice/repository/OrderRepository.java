package com.example.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.orderservice.entity.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // New method to find all orders by a user's ID (their email)
    List<Order> findByUserId(String userId);
}
