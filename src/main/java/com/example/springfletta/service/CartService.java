package com.example.springfletta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.springfletta.entity.CartItem;
import com.example.springfletta.entity.Product;
import com.example.springfletta.entity.ProductOption;
import com.example.springfletta.entity.User;
import com.example.springfletta.repository.CartItemRepository;
import com.example.springfletta.repository.ProductOptionRepository;
import com.example.springfletta.repository.ProductRepository;
import com.example.springfletta.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CartService {
	private final CartItemRepository cartItemRepository;
	private final ProductOptionRepository optionRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	
	public CartService(CartItemRepository cartItemRepository, ProductOptionRepository optionRepository, 
			ProductRepository productRepository, UserRepository userRepository) {
		this.cartItemRepository = cartItemRepository;
		this.productRepository = productRepository;
		this.optionRepository = optionRepository;
		this.userRepository =userRepository;
	}
	
	public List<CartItem> getCartByUser(String userId){
		List<CartItem> cartList = new ArrayList<CartItem>();
		cartList = cartItemRepository.findByUserId(userId);
		cartList.forEach(cartItem -> {
			Long productId = cartItem.getProduct().getId();
			Long optionId = cartItem.getProductOption().getId();
		    
		    Product product = productRepository.findById(productId).orElse(null);
		    ProductOption option = optionRepository.findById(optionId).orElse(null);
		    
		    cartItem.setProduct(product);
		    cartItem.setProductOption(option);
		});
		return cartList;
	}
	
	@Transactional
	public void addToCart(String userId, Long productId, Long optionId, Integer quantity) {
		
		User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        ProductOption option = optionRepository.findById(optionId)
                .orElseThrow(() -> new IllegalArgumentException("상품 옵션을 찾을 수 없습니다."));

        Optional<CartItem> existingCartItem = cartItemRepository.findByUserIdAndProductOptionId(userId, optionId);
        if(existingCartItem.isPresent()) {
        	//이미 있는 상품인 경우 수량만 증가
        	CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }else {
        	CartItem newCartItem = new CartItem();
        	newCartItem.setUser(user);
        	newCartItem.setProduct(option.getProduct()); // Product 설정
        	newCartItem.setProductOption(option);
        	newCartItem.setQuantity(quantity);
        	newCartItem.setShipping(0);
            cartItemRepository.save(newCartItem);
        }
	}
	
	@Transactional
    public CartItem updateCartQuantity(Long optionId, String actionType) {
		Optional<CartItem> cartItemOpt = cartItemRepository.findByProductOptionId(optionId);
		
		if(cartItemOpt.isPresent()) {
			CartItem cartItem = cartItemOpt.get();

            switch (actionType) {
                case "increase":
                    cartItemRepository.incrementQuantity(optionId);
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    break;
                case "decrease":
                    cartItemRepository.decrementQuantity(optionId);
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                    break;
                case "delete":
                    cartItemRepository.deleteByOptionId(optionId);
                    return null; // 삭제된 경우 null 반환
                default:
                    throw new IllegalArgumentException("Invalid actionType: " + actionType);
            }
            return cartItem;
		}
        throw new IllegalArgumentException("Cart item not found for optionId: " + optionId);
	}

	public Integer getQuantityByOptionId(Long optionId) {
		return cartItemRepository.findByProductOptionId(optionId)
				.map(CartItem::getQuantity)
                .orElse(0);
	}

}
