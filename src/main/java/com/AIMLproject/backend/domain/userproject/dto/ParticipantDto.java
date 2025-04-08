package com.AIMLproject.backend.domain.userproject.dto;

import com.AIMLproject.backend.domain.user.model.User;
import com.AIMLproject.backend.domain.userproject.model.UserProject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipantDto {

	private User user;
	private Boolean isOwner;
	private Boolean readOnly;

	public ParticipantDto(UserProject userProject) {
		this.user = userProject.getUser();
		this.isOwner = userProject.getIsOwner();
		this.readOnly = userProject.getReadOnly();
	}
}
