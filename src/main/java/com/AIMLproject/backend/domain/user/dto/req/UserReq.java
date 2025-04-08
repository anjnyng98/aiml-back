package com.AIMLproject.backend.domain.user.dto.req;

import lombok.Getter;

@Getter
public class UserReq {
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
}
