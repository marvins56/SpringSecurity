package com.example.SpringSecurity.auth.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringSecurity.auth.AuthenticationRequest;
import com.example.SpringSecurity.auth.AuthenticationResponse;
import com.example.SpringSecurity.auth.RegisterRequest;
import com.example.SpringSecurity.config.JwtService;
import com.example.SpringSecurity.model.ROLE;
import com.example.SpringSecurity.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	@Autowired
	private UserRepository repository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManger;

	public AuthenticationResponse register(RegisterRequest request) {
		// create user
		var user =  User.builder()
				.firstname(request.getFirstname())
				.lastname(request.getLastname())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(ROLE.USER)
				.build();
		
		repository.save(user); 
	
		var jwtToken = jwtService.generateToken(user);

		return AuthenticationResponse.builder().token(jwtToken).build();

	}

	// login
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		// if correct credentials
		authenticationManger
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user = repository.findByEmail(request.getEmail()).orElseThrow();

		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

}
