package com.example.demo.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.MyUser;

@Mapper
public interface UserMapper {
	
	//Userテーブルからusernameに一致する全件取得
	@Select("select * from User where username = #{username}")
	public MyUser findByUsername(String username);	
	
	//ユーザ追加
	@Insert("insert into User (username, password) values (#{username}, #{password})")
	public void insert(MyUser myUser);
	
	//ユーザ更新
	@Update("update User set username = #{username}, password = #{password}")
	public void update(MyUser myUser);
	
	//ユーザ消去
	@Delete("delete from User where dataId = #{dataId}")
	public void delete(String dateId);
	
}
