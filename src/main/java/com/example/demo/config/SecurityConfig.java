package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		return new BCryptPasswordEncoder();
	}
	
	@Override//セキュリテ設定
	protected void configure(HttpSecurity http) throws Exception{
		
		http
			.authorizeRequests()
			.antMatchers("/login").permitAll()//loginは認証なしでアクセス可能
			.antMatchers("/user_register").permitAll()//user_registerにも認証無しでアクセス可能
			.anyRequest().authenticated()//↑以降のURLリクエストは、loginしないとアクセス不可
			.and()
			
			.formLogin()//ログインの設定
			.loginProcessingUrl("/login")//ログイン処理のパス
			.loginPage("/login")//ログインページの指定
			.usernameParameter("username")
			.passwordParameter("password")
			.permitAll()
			.defaultSuccessUrl("/top")//ログインが成功したらtop.htmlにアクセス
			.and()
			.logout()//ログアウトの設定
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		//CSRF対策を無効に設定
		http.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		auth
			//userDetailsServiceを使って、認証を行う
			.userDetailsService(userDetailsService);
	}
}
