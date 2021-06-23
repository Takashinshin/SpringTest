package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SecurityController {
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/top")
	public String showTop(Authentication loginUser,Model model) {
		
		model.addAttribute("username", loginUser.getName());
		return "top";
	}

}
