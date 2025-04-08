package com.AIMLproject.backend.domain.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.AIMLproject.backend.global.jwt.JwtUtil;

@Service
public class AuthService {

	private final AuthenticationManager authManager;
	private final JwtUtil jwtUtil;

	@Autowired
	public AuthService(AuthenticationManager authManager, JwtUtil jwtUtil) {
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
	}

	public String generateToken(String username, String password) throws RuntimeException {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (RuntimeException e) {
			throw new RuntimeException("Invalid username or password"); // to do: handle exception
		}
		UserDetails userDetails = new User(username, password, new ArrayList<>());
		return jwtUtil.generateToken(userDetails);
	}
}
