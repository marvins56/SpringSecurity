package com.example.SpringSecurity.auth.service;

import org.springframework.stereotype.Service;

import com.example.SpringSecurity.auth.AuthenticationResponse;
import com.example.SpringSecurity.auth.requests.AuthenticationRequest;
import com.example.SpringSecurity.auth.requests.RegisterRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	public AuthenticationResponse register(RegisterRequest request) {
		//create user
		
		
		
		return null;
	}

	//login
	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
