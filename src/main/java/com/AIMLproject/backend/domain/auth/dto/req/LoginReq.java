package com.AIMLproject.backend.domain.auth.dto.req;

import lombok.Getter;

@Getter
public class LoginReq {
	private String username;
	private String password;
}
