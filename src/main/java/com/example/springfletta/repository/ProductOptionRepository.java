package com.example.springfletta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springfletta.entity.Product;
import com.example.springfletta.entity.ProductOption;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Long>{
	//List<ProductOption> findByProduct_Id(Long productId);
	List<ProductOption> findByProduct(Product product);

}
