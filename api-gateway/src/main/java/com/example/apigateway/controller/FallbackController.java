package com.example.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/productService")
    public String productServiceFallback() {
        return "Product Service is temporarily unavailable. Please try again later.";
    }

    @GetMapping("/orderService")
    public String orderServiceFallback() {
        return "Order Service is temporarily unavailable. Please try again later.";
    }

    @GetMapping("/inventoryService")
    public String inventoryServiceFallback() {
        return "Inventory Service is temporarily unavailable. Please try again later.";
    }
}
