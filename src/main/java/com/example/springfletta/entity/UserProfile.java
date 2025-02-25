package com.example.springfletta.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_profile") // 프로필 정보만 저장
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_id")
    private Long id;

    private String name;  
    private String email; 
    private String phone;
    private Timestamp created_at; 

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
