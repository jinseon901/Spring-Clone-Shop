package com.example.springfletta.repository;


import com.example.springfletta.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(String category); // 특정 카테고리별 상품 검색
    List<Product> findAll();
}
