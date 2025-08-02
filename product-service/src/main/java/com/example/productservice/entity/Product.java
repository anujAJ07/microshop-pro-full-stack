package com.example.productservice.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double price;
    private String description; // Added for more detail
    private String category;    // Added for filtering

    @ElementCollection // Tells JPA to store this list in a separate table
    private List<String> imageUrls;
}
