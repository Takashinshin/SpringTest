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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
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
			.defaultSuccessUrl("/top", true)//ログインが成功したらtop.htmlにアクセス
			.and()
			
			.logout()//ログアウトの設定
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutUrl("/logout")//POSTメソッドでログアウトする場合
			.logoutSuccessUrl("/login");//ログアウト成功時の遷移先;//logout先のURL指定
		
			//CSRF対策を一時的に無効
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
