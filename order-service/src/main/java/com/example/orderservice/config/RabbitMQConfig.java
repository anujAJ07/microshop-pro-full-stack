package com.example.orderservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RabbitMQConfig {

    // Queues this service PUBLISHES to
    public static final String ORDER_READY_FOR_PAYMENT_QUEUE = "order.ready_for_payment.queue";
    public static final String ORDER_CONFIRMED_QUEUE = "order.confirmed.queue"; // Renamed for clarity

    // Queue this service LISTENS to
    public static final String PAYMENT_SUCCESSFUL_QUEUE = "payment.successful.queue";

    @Bean
    public Queue orderReadyForPaymentQueue() {
        return new Queue(ORDER_READY_FOR_PAYMENT_QUEUE, true);
    }

    @Bean
    public Queue orderConfirmedQueue() {
        return new Queue(ORDER_CONFIRMED_QUEUE, true);
    }
    
    @Bean
    public Queue paymentSuccessfulQueue() {
        return new Queue(PAYMENT_SUCCESSFUL_QUEUE, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
