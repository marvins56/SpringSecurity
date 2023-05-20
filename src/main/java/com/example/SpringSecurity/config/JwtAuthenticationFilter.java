package com.example.SpringSecurity.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request, // our request
			@NonNull HttpServletResponse response, // our response
			@NonNull FilterChain filterChain // contains list of other filters we need
	) throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorisation"); // it contains the token
		final String Jwt;
		
		if()
	
	}

}
