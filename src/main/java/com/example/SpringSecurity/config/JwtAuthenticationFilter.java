package com.example.SpringSecurity.config;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.authentication.*;

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

	private final JwtService jwtService;

	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, // our request
			@NonNull HttpServletResponse response, // our response
			@NonNull FilterChain filterChain // contains list of other filters we need
	) throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization"); // it contains the token
		final String jwt;
		final String userEmail;

		if (authHeader == null || !authHeader.startsWith("bearer")) {
			filterChain.doFilter(request, response);
			return;
		}

		// extracting token from authorisation header
		jwt = authHeader.substring(7);
		userEmail = jwtService.extractUsername(jwt);
		// validation of credentials
		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			// get user from db
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
			// validate token by checking if its still valid or not
			if (jwtService.isTokenValid(jwt, userDetails)) {

				// if token is valid
				// used to update security context

				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,
						null, userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			filterChain.doFilter(request, response);

		}

	}

}
