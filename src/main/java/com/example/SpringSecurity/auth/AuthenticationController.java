package com.example.SpringSecurity.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringSecurity.auth.requests.AuthenticationRequest;
import com.example.SpringSecurity.auth.requests.RegisterRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vi/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	
	//register
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register( @RequestBody RegisterRequest request){
		
	}
	
	//auth
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate( @RequestBody AuthenticationRequest request){
		
	}
}
 