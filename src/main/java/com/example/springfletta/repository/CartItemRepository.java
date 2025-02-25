package com.example.springfletta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springfletta.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{
	List<CartItem> findByUserId(String userId);
	//카트에 이미 담겨있는 상품인지 확인
	Optional<CartItem> findByUserIdAndProductOptionId(String userId, Long optionId);
	
	Optional<CartItem> findByProductOptionId(Long optionId);

    // 수량 증가
    @Modifying
  //jpa 에서는 엔티티명 기준으로 작성
    @Query("UPDATE CartItem c " +
    	       "SET c.quantity = c.quantity + 1 " +
    	       "WHERE c.productOption.id = :optionId " +
    	       "AND c.quantity < (SELECT p.quantity FROM ProductOption p WHERE p.id = c.productOption.id)") //수량 초과 방지 조건
    void incrementQuantity(@Param("optionId") Long optionId);

    // 수량 감소
    @Modifying
    @Query("UPDATE CartItem c SET c.quantity = c.quantity - 1 WHERE c.productOption.id = :optionId AND c.quantity > 1")
    void decrementQuantity(@Param("optionId") Long optionId);

    // 상품 삭제
    @Modifying
    @Query("DELETE FROM CartItem c WHERE c.productOption.id = :optionId")
    void deleteByOptionId(@Param("optionId") Long optionId);
	
    //구매 성공 후 장바구니 비우기
    void deleteByUserId(String userId);
	
}
