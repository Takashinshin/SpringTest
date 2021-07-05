package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.model.MyUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class SecurityController {
	
	//ログイン画面遷移
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	//ログイン認証後、top画面遷移
	@GetMapping("/top")
	public String showTop(Authentication loginUser,Model model) {
		
		model.addAttribute("username", loginUser.getName());
		return "top";
	}
	
	//ユーザ新規登録画面遷移
	@GetMapping("/user_register")
	public String showRegister(@ModelAttribute MyUser myUser) {
		return "user_register";
	}
	
	//新規ユーザ更新処理
	
	
	
}
