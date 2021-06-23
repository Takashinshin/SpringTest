package com.example.demo.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;




public class MyUser implements UserDetails {
	
	@Setter
	@Getter
	private String username;
	
	@Setter
	@Getter
	private String password;

	private Collection<GrantedAuthority> authorities;
	
	
	//コンストラクタ
	public MyUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
//	@Override
//	public String getPassword() {
//		// TODO 自動生成されたメソッド・スタブ
//		return password;
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO 自動生成されたメソッド・スタブ
//		return username;
//	}

	public void setAuthorities(Collection<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

}
