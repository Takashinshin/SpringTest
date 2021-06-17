package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class TopForm {
	
	//top画面からの計算基準日
	@NotBlank
	@Pattern(regexp = "((19|[2-9][0-9])[0-9]{2})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])")
	private String baseDate;
	
	
	private List<ResultDate> results;
	
	//コンストラクタ
	public TopForm() {
	}
	
	public TopForm(String baseDate, List<CalcDate> results) {
		this.baseDate = baseDate;
		this.results = new ArrayList<>();
		results.stream().forEach(r -> this.results.add(convertToResult(r)));
		
		
	}
	
	
	// 日付計算式の内容を画面用の計算結果オブジェクトに変換する
	public ResultDate convertToResult(CalcDate date) {
		return new ResultDate(date);
	}
	
	

}
