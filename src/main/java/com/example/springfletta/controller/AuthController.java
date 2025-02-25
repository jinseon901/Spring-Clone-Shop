package com.example.springfletta.controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springfletta.util.JwtUtil;

@Controller
public class AuthController {
	private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    
    @GetMapping("/login")
	public String account() {
		return "user/account";
	}
    
    @PostMapping("/doLogin")
    public String login(@RequestParam String userId, @RequestParam String password) {
    	//로그인 정보 확인
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userId, password)
        );
        
        //인증 정보 확인
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "";
    }

}
