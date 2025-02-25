package com.example.springfletta.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springfletta.entity.Product;
import com.example.springfletta.entity.ProductOption;
import com.example.springfletta.repository.ProductOptionRepository;
import com.example.springfletta.repository.ProductRepository;

@Service
public class ProductViewService {

	private final ProductRepository productRepository;
	private final ProductOptionRepository optionRepository;
	
	public ProductViewService(ProductRepository productRepository, ProductOptionRepository optionRepository) {
		this.productRepository = productRepository;
		this.optionRepository = optionRepository;
	}
	
	public Product getProductById(Long productId) {
		Product product = productRepository.findById(productId).orElse(null);
		return product;
	}
	
	public List<ProductOption> getOptionByProduct(Product product) {
		//return optionRepository.findByProduct_Id(productId);
		return optionRepository.findByProduct(product);
	}
}
