package com.example.demo.model;

import javax.validation.constraints.NotBlank;



//計算式を表現するオブジェクト(MySQLより)
public class CalcDate {
	
	@NotBlank
	private String dateId;
	
	@NotBlank
	private String dateName;
	
	private int addYear;
	
	private int addMonth;
	
	private int addDay;

	public String getDateId() {
		return dateId;
	}

	public void setDateId(String dateId) {
		this.dateId = dateId;
	}

	public String getDateName() {
		return dateName;
	}

	public void setDateName(String dateName) {
		this.dateName = dateName;
	}

	public int getAddYear() {
		return addYear;
	}

	public void setAddYear(int addYear) {
		this.addYear = addYear;
	}

	public int getAddMonth() {
		return addMonth;
	}

	public void setAddMonth(int addMonth) {
		this.addMonth = addMonth;
	}

	public int getAddDay() {
		return addDay;
	}

	public void setAddDay(int addDay) {
		this.addDay = addDay;
	}
	
	
}
