package com.example.springfletta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springfletta.entity.Address;
import com.example.springfletta.entity.DeliveryStatus;
import com.example.springfletta.entity.Order;
import com.example.springfletta.entity.OrderDetail;
import com.example.springfletta.repository.AddressRepository;
import com.example.springfletta.repository.CartItemRepository;
import com.example.springfletta.repository.DeliveryStatusRepository;
import com.example.springfletta.repository.OrderDetailRepository;
import com.example.springfletta.repository.OrderRepository;
import com.example.springfletta.repository.ProductOptionRepository;
import com.example.springfletta.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	private final OrderRepository orderRepository;
	private final OrderDetailRepository detailRepository;
	private final DeliveryStatusRepository deliveryStatusRepository;
	private final AddressRepository addressRepository;
	
	public List<Order> getOrderListByUserId(String userId){
		List<Order> orderList = orderRepository.findByUserId(userId);
		orderList.forEach(order -> {
			DeliveryStatus status = deliveryStatusRepository.findById(order.getId()).orElse(null);
			List<OrderDetail> detailList = detailRepository.findByOrderId(order.getId());
			
			order.setDeliveryStatus(status);
			order.setOrderDetails(detailList);
		});
		return orderList;
	}
	
	public Address getAddressByUserId(String userId) {
		Address address = addressRepository.findByUserId(userId).orElse(null);
		return address;
	}
	
}
