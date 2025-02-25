package com.example.springfletta.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "delivery_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeliveryStatus {

	//해당 엔티티에서 굳이 Order 테이블 정보가 모두 필요하진 않아서 그냥 관계설정 생략
    @Id
    @Column(name = "order_id")
    private Long orderId;  // 배송이 걸린 주문 ID

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;  // 배송을 받는 사용자

    @Builder.Default
    @Column(name = "status", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer status = 0;  // 배송 상태 (0: 준비 중, 1: 배송 중, 2: 배송 완료)
}
