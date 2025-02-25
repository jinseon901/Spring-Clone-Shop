package com.example.springfletta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDto {
    private String paymentId;
    private String orderName;
    private int totalAmount;
    private AddressDto address;
    private UserDto user;
}
