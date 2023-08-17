package com.sun.board.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sun.board.filter.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
	
	private final JwtAuthenticationFilter JwtAuthenticationFilter;

	@Bean
	protected SecurityFilterChain configurure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity
		.cors().and()
		.csrf().disable()
		.httpBasic().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()
		.antMatchers("/", "/api/v1/auth/**", "/api/v1/search/**", "/file/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/v1/board/**").permitAll()
		.antMatchers(HttpMethod.GET,"/api/v1/user/*").permitAll()
		.anyRequest().authenticated();

		httpSecurity.addFilterBefore(JwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();
	}
}