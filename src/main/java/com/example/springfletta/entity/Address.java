package com.example.springfletta.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")  // ✅ 실제 DB 테이블명 지정
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;  // 주소 ID (PK)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) 
    private User user;  // 주소를 등록한 사용자

    @Column(name = "post_code", length = 10)
    private String postCode;  // 우편번호

    @Column(name = "address", length = 255)
    private String address;  // 기본 주소

    @Column(name = "detail_address", length = 255)
    private String detailAddress;  // 상세 주소

    @Column(name = "extra_address", length = 255)
    private String extraAddress;  // 참고 주소

	public void updateAddress(Address newAddress) {
        this.postCode = newAddress.getPostCode();
        this.address = newAddress.getAddress();
        this.detailAddress = newAddress.getDetailAddress();
        this.extraAddress = newAddress.getExtraAddress();
    }
    
}
