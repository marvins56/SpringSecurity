package com.example.SpringSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.SpringSecurity.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

	//user details service
	private final UserRepository userRepository;
	@Bean
	public UserDetailsService  userDetailsService() {
		return username -> userRepository.findByEmail(username)
				.orElseThrow(()-> new UsernameNotFoundException("user Not Found"));
		
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		//FETCHES DATA AND ENCODE PASSOWRDS ETC
		
	}
}
