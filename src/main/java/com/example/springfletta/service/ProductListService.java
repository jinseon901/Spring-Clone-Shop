package com.example.springfletta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springfletta.entity.Product;
import com.example.springfletta.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class ProductListService {
	//생성자 주입
	private final ProductRepository productRepository;
	
	public ProductListService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public List<Product> getProductListByCategory(String category) {
        List<Product> productList = productRepository.findByCategory(category);

        return productList;
    }
	
	public List<Product> getProduct(){
		return productRepository.findAll();
	}

}
