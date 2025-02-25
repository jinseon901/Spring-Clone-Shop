package com.example.springfletta.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
    public String main() {
        return "main";
    }
	
	@GetMapping("/menu")
	public String menu(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		if (userDetails != null) {
            model.addAttribute("loginId", userDetails.getUsername());
        }
		
		return "menu";
	}
	
	@GetMapping("/footer")
	public String footer() {
		return "footer";
	}

}
