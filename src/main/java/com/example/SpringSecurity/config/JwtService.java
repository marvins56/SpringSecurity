package com.example.SpringSecurity.config;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.*;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static
	final String SECRET_KEY = "34753778214125432A462D4A614E645267556B58703273357638792F423F4528482B4B6250655368566D597133743677397A24432646294A404E635166546A57"
		;
	public String extractUsername(String token) {
		return extractClaim(token, Claims:: getSubject);
	}
	public <T> T extractClaim(String  token, Function<Claims,T> claimsRessolver) {
		final Claims claims = extractAllClaims(token);
		
		return claimsRessolver.apply(claims);
	}

	public String generateToken(Map<String,Object> extractClaims, UserDetails userDetails) {
		
		return Jwts.builder()
				.setClaims(extractClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24
						))
				.signWith(getSignInKey(), SignatureAlgorithm.HS512)
				.compact();		
		
	}
	
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
		
	}
	//method to validatet  a token
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
		
	}
	private boolean isTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date());
	}
	
	//extract expirattion date
	private Date extractExpiration(String token) {
		
		return extractClaim(token, Claims::getExpiration);
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
