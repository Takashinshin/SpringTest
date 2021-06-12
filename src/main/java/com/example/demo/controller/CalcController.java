package com.example.demo.controller;


import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.CalcDate;
import com.example.demo.model.SearchDate;
import com.example.demo.service.CalcService;


@Controller
public class CalcController {
	
	@Autowired
	private CalcService calcService;
	
	
	//top画面遷移　top.html
	@GetMapping("/")
	public String getDate(@ModelAttribute SearchDate searchDate ,Model model) {
		return "top";
	}
	//全件データ取得表示
	@GetMapping("/searchAll")
	public String searchAll(@ModelAttribute @Validated SearchDate searchDate, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "top";
		}
		//全件データ取得
		List<CalcDate> getResult = calcService.searchAll();
		Map<Integer, Object> calcResult = new LinkedHashMap<>();//計算結果の変数
		
		for(int i = 0; i < getResult.size(); i++) {
			CalcDate date = getResult.get(i);
			LocalDate result  = searchDate.getSearchDate();
			result = result.plusYears(date.getAddYear())
							.plusMonths(date.getAddMonth())
							.plusDays(date.getAddDay());
			calcResult.put(i, result);
		}
		model.addAttribute("getResult", getResult);
		model.addAttribute("calcResult", calcResult);
		
		return "redirect:/top";
	}

	
	
	//register画面 初期表示
	@GetMapping("/register")
	public String getRegister(@ModelAttribute CalcDate date,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "top";
		}
		return "register";
	}
	//新規登録処理
	@PostMapping("/register")
	public String register(@ModelAttribute CalcDate date) {
		calcService.register(date);
		
		return "redirect:/top";
	}
	
	
	
	//更新画面 & 一件選択データ取得
	@GetMapping("/update/{dateId}")
	public String getUpdate(@PathVariable("dateId")String dateId, Model model) {
		CalcDate updateDate = calcService.selectOne(dateId);
		model.addAttribute("updateDate", updateDate);
		
		return "update";
	}
	//更新処理
	@PostMapping("/update/{dateId}")
	public String update(@ModelAttribute CalcDate date) {
		calcService.update(date);
		return "top";
	}
	
	
	
	//消去処理
	@GetMapping("/delete/{dateId}")
	public String delete(@PathVariable String dateId) {
		calcService.delete(dateId);
		return "top";
	}
}
