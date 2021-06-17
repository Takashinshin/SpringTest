package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CalcDate;
import com.example.demo.repository.CalcMapper;

@Service
public class CalcService {
	
	@Autowired
	private CalcMapper calcMapper;
	
	//計算式の全件取得
	public List<CalcDate> searchAll(){
		return calcMapper.selectAll();
	}
	
	//日付計算式一件を取得
	public CalcDate selectOne(String dateId) {
		return calcMapper.selectOne(dateId);
	}
	
	//日付計算式を追加
	public void register(CalcDate date){
		calcMapper.insert(date);
	}
	
	//日付計算式の更新処理
	public void update(CalcDate date) {
		calcMapper.update(date);
	}
	
	//日付計算式の消去処理
	public void delete(String dateId) {
		calcMapper.deletecalc(dateId);
	}
	
	//日付計算の処理
	public String calclate(String baseDate, CalcDate calcDate) {
		LocalDate date = LocalDate.parse(baseDate, DateTimeFormatter.ofPattern("yyyyMMdd"));
		LocalDate calculatedDate = date.plusYears(calcDate.getAddYear())
									   .plusMonths(calcDate.getAddMonth())
									   .plusDays(calcDate.getAddDay());
		return calculatedDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	}
}
