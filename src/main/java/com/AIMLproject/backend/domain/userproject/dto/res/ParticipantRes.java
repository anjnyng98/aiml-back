package com.AIMLproject.backend.domain.userproject.dto.res;

import com.AIMLproject.backend.domain.userproject.model.UserProject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantRes {

	private Long userId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private Boolean isOwner;
	private Boolean readOnly;

	public ParticipantRes(UserProject userProject) {
		this.userId = userProject.getUser().getUserId();
		this.username = userProject.getUser().getUsername();
		this.firstName = userProject.getUser().getFirstName();
		this.lastName = userProject.getUser().getLastName();
		this.email = userProject.getUser().getEmail();
		this.isOwner = userProject.getIsOwner();
		this.readOnly = userProject.getReadOnly();
	}
}
