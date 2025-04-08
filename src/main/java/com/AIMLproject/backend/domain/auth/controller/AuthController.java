package com.AIMLproject.backend.domain.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AIMLproject.backend.domain.auth.dto.req.LoginReq;
import com.AIMLproject.backend.domain.auth.dto.res.LoginRes;
import com.AIMLproject.backend.domain.auth.service.AuthService;

@RestController
@RequestMapping("/api")
public class AuthController {

	private final AuthService authService;

	@Autowired
	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	/** new version */

	@PostMapping("/auth/login")
	public ResponseEntity<LoginRes> login(@RequestBody LoginReq req) {
		String token = authService.generateToken(req.getUsername(), req.getPassword());
		LoginRes res = new LoginRes(token);
		return ResponseEntity.ok(res);
	}
}
