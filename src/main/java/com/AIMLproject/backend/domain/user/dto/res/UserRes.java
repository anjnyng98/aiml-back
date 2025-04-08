package com.AIMLproject.backend.domain.user.dto.res;

import java.time.LocalDateTime;

import com.AIMLproject.backend.domain.user.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRes {

	private Long userId;
	private LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
	private String username;
	private String firstName;
	private String lastName;
	private String email;

	public UserRes(User user) {
		this.userId = user.getUserId();
		this.createdAt = user.getCreatedAt();
		this.lastModifiedAt = user.getLastModifiedAt();
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
	}

}
