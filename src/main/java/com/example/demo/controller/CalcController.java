package com.example.demo.controller;


import java.util.List;

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
import com.example.demo.model.ResultDate;
import com.example.demo.model.TopForm;
import com.example.demo.service.CalcService;



@Controller
public class CalcController {
	
	@Autowired
	private CalcService calcService;
	
	
	//top画面遷移　top.html
	@GetMapping("/")
	public String getDate(@ModelAttribute TopForm form) {
		return "top";
	}
	//全件データ取得表示
	@PostMapping("/searchAll")
	public String searchAll(@ModelAttribute @Validated TopForm date, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "top";
		}
		TopForm resultForm = new TopForm(date.getBaseDate(), calcService.searchAll());
		List<ResultDate> results = resultForm.getResults();
		
		//results.stream().forEach(e -> e.setCalculated(service.calculate(form.getBaseDate(), e.getFormula())));
		
		for(ResultDate r : results) {
			r.setCalculated(calcService.calclate(date.getBaseDate(), r.getDate()));
		}
		
		model.addAttribute("results", results);
		return "top";
	}


	
	//register画面 初期表示
	@GetMapping("/register")
	public String getRegister(@ModelAttribute CalcDate date) {
		return "register";
	}
	
	//新規登録処理
	@PostMapping("/register")
	public String register(@ModelAttribute CalcDate date, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "register";
		}
		
		calcService.register(date);
		model.addAttribute("topForm", new TopForm());
		return "top";
	}
	
	
	//更新画面遷移 &　一件取得
	@GetMapping("/update/{dateId}")
	public String getUpdate(@PathVariable String dateId, Model model) {
		model.addAttribute("calcDate", calcService.selectOne(dateId));
		return "update";
	}
	
	//更新処理
	@PostMapping("/update/{dateId}")
	public String update(@ModelAttribute @Validated CalcDate date, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "update";
		}
		calcService.update(date);
		model.addAttribute("topForm", new TopForm());
		return "top";
	}
	
	
	//消去処理
	@PostMapping("/delete/{dateId}")
	public String delete(@PathVariable String dateId, Model model) {
		calcService.delete(dateId);
		model.addAttribute("topForm", new TopForm());
		return "top";
	}
}
