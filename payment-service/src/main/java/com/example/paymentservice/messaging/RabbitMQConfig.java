package com.example.paymentservice.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String ORDER_READY_FOR_PAYMENT_QUEUE = "order.ready_for_payment.queue";
    public static final String PAYMENT_SUCCESSFUL_QUEUE = "payment.successful.queue";

    @Bean
    public Queue orderReadyForPaymentQueue() {
        return new Queue(ORDER_READY_FOR_PAYMENT_QUEUE, true);
    }

    @Bean
    public Queue paymentSuccessfulQueue() {
        return new Queue(PAYMENT_SUCCESSFUL_QUEUE, true);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
