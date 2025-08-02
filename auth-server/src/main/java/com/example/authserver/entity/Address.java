package com.example.authserver.entity;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable // Marks this as a component that can be embedded in other entities
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}

