package com.example.springfletta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springfletta.entity.DeliveryStatus;

@Repository
public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatus, Long>{
	

}
