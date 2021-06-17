package com.example.demo.model;

import java.util.Arrays;
import java.util.StringJoiner;

public class ResultDate {
	
	//日付計算式
	private CalcDate date;
	
	//計算結果
	private String calculated;
	
	//コンストラクタ
	public ResultDate(CalcDate date) {
		this.date = date;
	}
	
	public String getDateId() {
		return date.getDateId();
	}
	
	public String getDateName() {
		return date.getDateName();
	}
	public CalcDate getDate() {
		return date;
	}
	
	public String getCalculated() {
		return calculated;
	}
	
	public void setCalculated(String calculated) {
		this.calculated = calculated;
	}
	
	//年月日で区切られた計算式を取得します 「/」で仕切る
	public String getYmdDate() {
		int[] ymdDate = {date.getAddYear(),
						 date.getAddMonth(), 
						 date.getAddDay()};
		StringJoiner joiner = new StringJoiner(" / ");
		Arrays.stream(ymdDate).forEach(i -> joiner.add(String.valueOf(i)));
		return joiner.toString();
	}
}
