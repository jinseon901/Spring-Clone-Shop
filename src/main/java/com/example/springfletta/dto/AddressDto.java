package com.example.springfletta.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressDto {
    private String postCode;
    private String address;
    private String detailAddress;
    private String extraAddress;
}