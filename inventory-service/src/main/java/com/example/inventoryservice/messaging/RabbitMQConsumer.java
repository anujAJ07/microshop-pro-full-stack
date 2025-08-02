package com.example.inventoryservice.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.inventoryservice.entity.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;

@Component
public class RabbitMQConsumer {

    @Autowired
    private InventoryRepository inventoryRepository;

    // This method will now listen for messages on the "order.confirmed.queue"
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void handleOrderConfirmedEvent(OrderConfirmedEvent event) {
        System.out.println("Received order CONFIRMED event: " + event);

        Inventory inventory = inventoryRepository.findByProductId(event.productId())
                .orElseThrow(() -> new RuntimeException("Inventory not found for product: " + event.productId()));

        inventory.setQuantity(inventory.getQuantity() - event.quantity());
        inventoryRepository.save(inventory);

        System.out.println("Inventory updated for product: " + event.productId());
    }
}