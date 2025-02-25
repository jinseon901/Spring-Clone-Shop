package com.example.springfletta.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springfletta.dto.PaymentRequestDto;
import com.example.springfletta.service.PaymentCompleteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PaymentController {
	private final PaymentCompleteService paymentCompleteService;
	
	@PostMapping("/payment_complete")
	public String paymentCheck(@AuthenticationPrincipal UserDetails userDetails, @RequestBody PaymentRequestDto requestDto) {
		String userId = userDetails.getUsername();
		paymentCompleteService.processPayment(userId, requestDto);
		return "user/orderList";
	}

}
