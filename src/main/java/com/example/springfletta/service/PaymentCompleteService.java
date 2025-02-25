package com.example.springfletta.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.springfletta.dto.AddressDto;
import com.example.springfletta.dto.PaymentRequestDto;
import com.example.springfletta.dto.UserDto;
import com.example.springfletta.entity.Address;
import com.example.springfletta.entity.CartItem;
import com.example.springfletta.entity.DeliveryStatus;
import com.example.springfletta.entity.Order;
import com.example.springfletta.entity.OrderDetail;
import com.example.springfletta.entity.ProductOption;
import com.example.springfletta.entity.User;
import com.example.springfletta.repository.AddressRepository;
import com.example.springfletta.repository.CartItemRepository;
import com.example.springfletta.repository.DeliveryStatusRepository;
import com.example.springfletta.repository.OrderDetailRepository;
import com.example.springfletta.repository.OrderRepository;
import com.example.springfletta.repository.ProductOptionRepository;
import com.example.springfletta.repository.UserRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //lombok으로 생성자 자동 생성
public class PaymentCompleteService {
	private final CartItemRepository cartItemRepository;
	private final ProductOptionRepository optionRepository;
	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final AddressRepository addressRepository;
	private final DeliveryStatusRepository deliveryStatusRepository;
	
	//포트원 시크릿키
	@Value("${portone.secret-key}")
	private static String PORTONE_API_SECRET;

    @Transactional
    public void processPayment(String userId, PaymentRequestDto paymentRequestDto) {
        try {
        	 String paymentId = paymentRequestDto.getPaymentId();
             String orderName = paymentRequestDto.getOrderName();
             int totalAmount = paymentRequestDto.getTotalAmount();

             User user = userRepository.findById(userId)
                     .orElseThrow(() -> new RuntimeException("User not found"));

             AddressDto addressDto = paymentRequestDto.getAddress();
             Address address = Address.builder()
                     .postCode(addressDto.getPostCode())
                     .address(addressDto.getAddress())
                     .detailAddress(addressDto.getDetailAddress())
                     .extraAddress(addressDto.getExtraAddress())
                     .user(user)
                     .build();

             // 사용자 정보 가져오기 (미사용)
             UserDto userDto = paymentRequestDto.getUser();
             String userName = userDto.getName();
             String userPhone = userDto.getPhone();
             String userEmail = userDto.getEmail();

            // 2. 포트원 결제 검증 API 호출
            JsonObject payment = verifyPayment(paymentId);
            if (totalAmount == payment.getAsJsonObject("amount").get("total").getAsInt()) {
                processOrder(userId, orderName, totalAmount, address);
            } else {
                throw new RuntimeException("Payment amount mismatch");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error processing payment: " + e.getMessage());
        }
    }

    private JsonObject verifyPayment(String paymentId) {
        RestTemplate restTemplate = new RestTemplate();
        String encodedPaymentId = URLEncoder.encode(paymentId, StandardCharsets.UTF_8);
        String url = "https://api.portone.io/payments/" + encodedPaymentId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "PortOne " + PORTONE_API_SECRET);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Payment verification failed: " + response.getBody());
        }

        return JsonParser.parseString(response.getBody()).getAsJsonObject();
    }

    private void processOrder(String userId, String orderName, int totalAmount, Address address) {
        // 1. 주문 저장
        final Order order = Order.builder()
                .orderName(orderName)
                .totalAmount(totalAmount)
                .user(userRepository.findById(userId).orElseThrow())
                .build();
        orderRepository.save(order);

        // 2. 주문 상세 저장
        List<CartItem> cartList = cartItemRepository.findByUserId(userId);
        cartList.forEach(cart -> {
            OrderDetail orderDetail = OrderDetail.builder()
                    .order(order)
                    .product(cart.getProduct())
                    .productOption(cart.getProductOption())
                    .quantity(cart.getQuantity())
                    .build();
            orderDetailRepository.save(orderDetail);
        });

        // 3. 주소 저장 (기존 주소 업데이트 or 새 주소 저장)
        addressRepository.findByUserId(userId)
                .ifPresentOrElse(existingAddress -> {
                    existingAddress.updateAddress(address);
                }, () -> addressRepository.save(address));

        // 4. 배송 상태 저장
        DeliveryStatus deliveryStatus = DeliveryStatus.builder()
                .orderId(order.getId())
                .user(userRepository.findById(userId).orElseThrow())
                .status(1)  // 결제 완료 상태
                .build();
        deliveryStatusRepository.save(deliveryStatus);

        // 5. 장바구니 비우기 & 상품 수량 업데이트
        cartItemRepository.deleteByUserId(userId);
        cartList.forEach(cart -> {
            ProductOption option = cart.getProductOption();
            option.setQuantity(option.getQuantity() - cart.getQuantity());
            optionRepository.save(option);
        });
    }
	
	
}
