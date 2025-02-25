package com.example.springfletta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springfletta.entity.Order;
import com.example.springfletta.entity.OrderDetail;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByUserId(String userId);
	

}
