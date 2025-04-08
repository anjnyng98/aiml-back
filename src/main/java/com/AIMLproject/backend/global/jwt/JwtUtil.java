package com.AIMLproject.backend.global.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

	@Value("${jwt.secret.key}")
	private String secretKey;

	public String generateToken(UserDetails userDetails) {
		long expirationMillis = 1000 * 60 * 60 * 24; // 24 hours
		return Jwts.builder()
			.setSubject(userDetails.getUsername())
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expirationMillis))
			.signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
			.compact();
	}

	/**
	 * to do: handle exception
	 */

	public Claims extractClaims(String token) {
		return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(token).getBody();
	}

	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}

	public Boolean isTokenExpired(String token) {
		return extractClaims(token).getExpiration().before(new Date());
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
}
