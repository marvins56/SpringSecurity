package com.example.SpringSecurity.auth.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class RegisterRequest {
	
	private String firstname;
	private String lastname;
	private String email;
	private String password;

}
