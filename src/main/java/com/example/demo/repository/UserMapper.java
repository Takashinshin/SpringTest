package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.model.MyUser;

@Mapper
public interface UserMapper {
	
	//Userテーブルからusernameに一致する全件取得
	@Select("select * from User where username = #{username}")
	public MyUser findByUsername(String username);	
}
