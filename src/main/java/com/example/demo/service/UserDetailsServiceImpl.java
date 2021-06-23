package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.MyUser;
import com.example.demo.repository.UserMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	//Mapperインスタンス化
	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//findByUsernameで見つけたユーザ名をmyUserに入れる
		 MyUser myUser = userMapper.findByUsername(username);
		if(myUser == null) {
			throw new UsernameNotFoundException(username + "not found");
		}
		UserDetails userDetails = (UserDetails) new MyUser(myUser.getUsername(), myUser.getPassword());
		return userDetails;
	}

}
