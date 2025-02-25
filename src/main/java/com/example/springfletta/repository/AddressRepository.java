package com.example.springfletta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springfletta.entity.Address;
import com.example.springfletta.entity.User;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long>{

	Optional<Address> findByUserId(String userId);

}
