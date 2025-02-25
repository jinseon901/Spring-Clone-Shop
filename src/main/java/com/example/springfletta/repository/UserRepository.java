package com.example.springfletta.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springfletta.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
