package com.example.springfletta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long id;  // 주문 상세 ID

    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;  // 주문 ID (주문과 연결됨)

    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "product_id", nullable = false) 
    private Product product;  // 상품 ID (상품과 연결됨)

    @ManyToOne(fetch = FetchType.LAZY)  
    @JoinColumn(name = "option_id", nullable = false)
    private ProductOption productOption;  // 옵션 ID (상품 옵션과 연결됨)

    @Column(name = "quantity", nullable = false)
    private Integer quantity;  // 구매 수량
}
