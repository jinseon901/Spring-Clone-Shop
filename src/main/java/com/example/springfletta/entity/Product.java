package com.example.springfletta.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가
    @Column(name = "product_id")
    private Long id;
    
	private String category;
    private String description;
    private String name;
    private String img_s;
    private String img_w;
    private int price;

    @OneToMany
    private List<ProductOption> options;
}
