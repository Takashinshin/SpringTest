package com.example.demo.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SearchDate {
	
	@DateTimeFormat(pattern = "yyyyMMdd")
	private LocalDate searchDate;

}
