package com.example.demo.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;


//計算式を表現するオブジェクト(MySQLより)
@Data
public class CalcDate {
	
	@NotBlank
	private String dateId;
	
	@NotBlank
	private String dateName;
	
	@NotBlank
	private int addYear;
	
	@NotBlank
	private int addMonth;
	
	@NotBlank
	private int addDay;
	

}
