package com.example.orderservice.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.orderservice.OrderStatus;
import com.example.orderservice.config.RabbitMQConfig;
import com.example.orderservice.entity.Order;
import com.example.orderservice.messaging.Events.OrderConfirmedEvent;
import com.example.orderservice.messaging.Events.PaymentSuccessfulEvent;
import com.example.orderservice.repository.OrderRepository;

@Component
public class OrderEventListener {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.PAYMENT_SUCCESSFUL_QUEUE)
    public void handlePaymentSuccessfulEvent(PaymentSuccessfulEvent event) {
        System.out.println("Received payment successful event for order ID: " + event.orderId());

        // 1. Find the order in the database
        Order order = orderRepository.findById(event.orderId())
                .orElseThrow(() -> new RuntimeException("Order not found: " + event.orderId()));

        // 2. Update the order status to PAID
        order.setStatus(OrderStatus.PAID);
        orderRepository.save(order);
        System.out.println("Order ID " + order.getId() + " status updated to PAID.");

        // 3. Publish the final OrderConfirmedEvent for the inventory service
        OrderConfirmedEvent confirmedEvent = new OrderConfirmedEvent(
                order.getId(),
                order.getProductId(),
                order.getQuantity()
        );
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_CONFIRMED_QUEUE, confirmedEvent);
        System.out.println("Published order confirmed event for order ID: " + order.getId());
    }
}
