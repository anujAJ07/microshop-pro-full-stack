package com.example.orderservice.messaging;

public class Events {
	
	// Published by Order Service when an order is created
	public record OrderReadyForPaymentEvent(Long orderId) {}

	// Published by Payment Service when payment is successful
	record PaymentSuccessfulEvent(Long orderId) {}

	// Published by Order Service when an order is confirmed and paid
	record OrderConfirmedEvent(Long orderId, Long productId, int quantity) {}


}
