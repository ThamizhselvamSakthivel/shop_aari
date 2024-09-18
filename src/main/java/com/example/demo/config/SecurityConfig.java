package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/*
@Configuration
@EnableWebSecurity*/
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilerChain(HttpSecurity http)throws Exception{
		/*
		 * http .authorizeHttpRequests(req-> req.requestMatchers("/login").permitAll()
		 * .anyRequest().authenticated());
		 * 
		 * */
		 return http.build();
		 
	}
	
}
