package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Override//セキュリテ設定
	protected void configure(HttpSecurity http) throws Exception{
		
		http
			.authorizeRequests()
			.antMatchers("/login").permitAll()//loginは認証なしでアクセス可能
			.anyRequest().authenticated()//login以降の全てのURLリクエストは、loginしないといけない
			.and()
			
			.formLogin()//ログインの設定
			.loginProcessingUrl("/login")//ログイン処理のパス
			.loginPage("/login")//ログインするページの指定
			.usernameParameter("username")
			.passwordParameter("password")
			.permitAll()
			.defaultSuccessUrl("/top")//ログインが成功したらtop.htmlにアクセス
			.and()
			
			.logout()//ログアウトの設定
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		
		auth
			//userDetailsServiceを使って、認証を行う
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
}
