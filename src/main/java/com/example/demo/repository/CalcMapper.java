package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.model.CalcDate;

@Mapper
public interface CalcMapper {
		//計算式を全件取得
		@Select("select * from dateform order by dateId")
		public List<CalcDate> selectAll(); 
		
		//計算式を一件取得
		@Select("select * from dateform where dateId = #{dateId}")
		public CalcDate selectOne(String dateId);
		
		//計算式を追加
		@Insert("insert into dateform (dateId, dateName, addYear, addMonth, addDay) values (#{dateId}, #{dateName}, #{addYear}, #{addMonth}, #{addDay})")
		public void insert(CalcDate date);
		
		//日付計算式を更新
		@Update("update dateform set dateName = #{dateName}, addYear = #{addYear}, addMonth = #{addMonth}, addDay = #{addDay} where dateId = #{dateId}")
		public void update(CalcDate date);

		//日付計算式を消去
		@Delete("delete from dateform where dateId = #{dateId}")
		public void deletecalc(String dateId);
		
}
