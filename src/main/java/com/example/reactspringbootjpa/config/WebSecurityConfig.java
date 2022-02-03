package com.example.reactspringbootjpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

import com.example.reactspringbootjpa.security.JwtAuthenticationFilter;

import lombok.extern.slf4j.Slf4j;

@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http 시큐리티 빌더
		http.cors() // WebMvcConfig에서 이미 설정했으므로 기본 cors설정
			.and()
			.csrf() // csrf는 현재 사용하지 않으므로 disable
			.disable()
			.httpBasic() // token을 사용하므로 basic 인증 disable
			.disable()
			.sessionManagement() // session 기반이 아님을 선언
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers("/", "/auth/**").permitAll() //  /와 /auth/** 경로는 인증 안해도 됨
			.anyRequest() //  /와 /auth/** 이외의 모든 경로는 인증해야됨
			.authenticated();
		
		// 매 http 요청마다 CorsFilter필터 실행후에 jwtAuthenticationFilter가 실행되도록 필터 등록
		http.addFilterAfter(
				jwtAuthenticationFilter,
				CorsFilter.class);
	}
}
