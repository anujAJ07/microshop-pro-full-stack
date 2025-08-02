package com.example.orderservice.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.orderservice.OrderStatus;
import com.example.orderservice.config.JwtUtil;
import com.example.orderservice.config.RabbitMQConfig;
import com.example.orderservice.entity.Order;
import com.example.orderservice.messaging.Events.OrderReadyForPaymentEvent;
import com.example.orderservice.repository.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @RequestBody Order orderRequest,
            @RequestHeader("Authorization") String token) {
        try {
            String jwt = token.substring(7);
            String userId = jwtUtil.extractUsername(jwt);
            String productServiceUrl = "http://product-service/products/" + orderRequest.getProductId();
            ProductDTO product = restTemplate.getForObject(productServiceUrl, ProductDTO.class);

            if (product != null) {
                // 1. Calculate price and set initial status
                double totalPrice = product.getPrice() * orderRequest.getQuantity();
                orderRequest.setTotalPrice(totalPrice);
                orderRequest.setUserId(userId);
                orderRequest.setStatus(OrderStatus.PENDING_PAYMENT); // Set initial status

                // 2. Save the order
                Order savedOrder = orderRepository.save(orderRequest);

                // 3. Publish the event for the PAYMENT service
                OrderReadyForPaymentEvent event = new OrderReadyForPaymentEvent(savedOrder.getId());
                rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_READY_FOR_PAYMENT_QUEUE, event);
                System.out.println("Published order ready for payment event for order ID: " + savedOrder.getId());

                return ResponseEntity.ok(savedOrder);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        } catch (HttpClientErrorException.NotFound ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/my-history")
    public ResponseEntity<List<OrderHistoryDTO>> getMyOrders(@RequestHeader("Authorization") String token) {
        // This method remains the same
        try {
            String jwt = token.substring(7);
            String userId = jwtUtil.extractUsername(jwt);
            List<Order> orders = orderRepository.findByUserId(userId);
            List<OrderHistoryDTO> orderHistory = orders.stream().map(order -> {
                try {
                    String url = "http://product-service/products/" + order.getProductId();
                    ProductDTO product = restTemplate.getForObject(url, ProductDTO.class);
                    return new OrderHistoryDTO(
                        order.getId(),
                        order.getProductId(),
                        product.getName(),
                        product.getImageUrls() != null && !product.getImageUrls().isEmpty() ? product.getImageUrls().get(0) : null,
                        order.getQuantity(),
                        order.getTotalPrice(),
                        order.getStatus().toString() // Add status to the DTO
                    );
                } catch (Exception e) {
                    return new OrderHistoryDTO(order.getId(), order.getProductId(), "Product Not Found", null, order.getQuantity(), order.getTotalPrice(), order.getStatus().toString());
                }
            }).collect(Collectors.toList());
            return ResponseEntity.ok(orderHistory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

// Add status to the OrderHistoryDTO
@Data
@AllArgsConstructor
@NoArgsConstructor
class OrderHistoryDTO {
    private Long orderId;
    private Long productId;
    private String productName;
    private String productImageUrl;
    private int quantity;
    private double totalPrice;
    private String status;
}

@Data
class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private List<String> imageUrls;
}
