package com.example.springfletta.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

//모든 컨트롤러에서 model.addAttribute("loginId", userId);를 자동으로 수행
@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("loginId")
    public String addUserIdToModel() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return null; // 로그인하지 않은 경우 null 반환
    }
}

