package com.example.productservice.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.productservice.entity.Product;
import com.example.productservice.repository.ProductRepository;

import java.util.List;

@RestController // Tells Spring this class defines REST endpoints
@RequestMapping("/products") // All endpoints in this class will start with /products
public class ProductController {

    @Autowired // Asks Spring to inject the ProductRepository instance it created
    private ProductRepository productRepository;

    @GetMapping("/hello")
    public String hello() {
    	return "welcome";
    }
    
    // Handles HTTP GET requests to /products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    // Handles HTTP POST requests to /products
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        // The @RequestBody annotation tells Spring to convert the incoming JSON into a Product object
        return productRepository.save(product);
    }
    @DeleteMapping("/all")
    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
