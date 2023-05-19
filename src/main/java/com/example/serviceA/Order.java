package com.example.serviceA;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private String id;
    private String email;
    private String phone_number;
    private Double parcel_weight;
    private String country;

}
