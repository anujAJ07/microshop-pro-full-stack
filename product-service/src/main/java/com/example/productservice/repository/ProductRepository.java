package com.example.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.productservice.entity.Product;

//We extend JpaRepository, specifying the Entity type (Product) and its Primary Key type (Long)
public interface ProductRepository extends JpaRepository<Product, Long> {
 // That's it! We now have methods like save(), findById(), findAll(), delete() for free!
}
