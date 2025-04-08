package com.AIMLproject.backend.domain.project.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.AIMLproject.backend.domain.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class Project {

	@Id
	@GeneratedValue
	private Long projectId;

	@JsonIgnore
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime lastModifiedAt;

	private Boolean isPublic;

	private String title;

	private String subtitle;

	public Project(User user, Boolean isPublic, String title, String subtitle) {
		this.user = user;
		this.isPublic = isPublic ? isPublic : false;
		this.title = title;
		this.subtitle = subtitle;
	}

	public Project() {

	}
}
