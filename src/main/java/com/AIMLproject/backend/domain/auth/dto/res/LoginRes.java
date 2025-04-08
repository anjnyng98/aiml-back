package com.AIMLproject.backend.domain.auth.dto.res;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRes {

	String token;

	public LoginRes(String token) {
		this.token = token;
	}
}
