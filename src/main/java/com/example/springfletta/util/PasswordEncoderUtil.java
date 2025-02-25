package com.example.springfletta.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 테스트용 코드(비밀번호 변환용)
public class PasswordEncoderUtil {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "1111"; // 사용자가 입력한 비밀번호
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("BCrypt 암호화된 비밀번호: " + encodedPassword);
    }
}
