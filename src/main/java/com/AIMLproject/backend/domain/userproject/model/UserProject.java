package com.AIMLproject.backend.domain.userproject.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.AIMLproject.backend.domain.project.model.Project;
import com.AIMLproject.backend.domain.user.model.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class UserProject {

	@Id
	@GeneratedValue
	private Long userProjectId;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Project project;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime lastModifiedAt;

	private Boolean isOwner;

	private Boolean readOnly;

	public UserProject(User user, Project project, Boolean isOwner, Boolean readOnly) {
		this.user = user;
		this.project = project;
		this.isOwner = isOwner;
		this.readOnly = readOnly;
	}

	public UserProject() {
	}
}
