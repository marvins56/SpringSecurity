package com.example.SpringSecurity.config;

import java.security.Key;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static
	final String SECRET_KEY = "34753778214125432A462D4A614E645267556B58703273357638792F423F4528482B4B6250655368566D597133743677397A24432646294A404E635166546A57"
		;
	public String extractUsername(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	
	//extract all claims
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey()) // Verifys the token ia theh one it claims it is 
				.build()
				.parseClaimsJws(token)
				.getBody();
	}


	private Key getSignInKey() {
	byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
