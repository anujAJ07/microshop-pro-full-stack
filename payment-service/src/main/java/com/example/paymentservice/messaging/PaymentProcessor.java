package com.example.paymentservice.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessor {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = RabbitMQConfig.ORDER_READY_FOR_PAYMENT_QUEUE)
    public void handleOrderReadyForPayment(OrderReadyForPaymentEvent event) {
        System.out.println("Received order ready for payment event: " + event);

        // 1. Simulate payment processing
        try {
            System.out.println("Processing payment for order ID: " + event.orderId());
            // Simulate a 2-second delay for payment processing
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Payment processing was interrupted for order ID: " + event.orderId());
            // In a real app, you might publish a PaymentFailedEvent here
            return;
        }

        // 2. If successful, create the success event
        PaymentSuccessfulEvent successEvent = new PaymentSuccessfulEvent(event.orderId());

        // 3. Publish the payment successful event
        rabbitTemplate.convertAndSend(RabbitMQConfig.PAYMENT_SUCCESSFUL_QUEUE, successEvent);
        System.out.println("Published payment successful event for order ID: " + event.orderId());
    }
}
