package com.AIMLproject.backend.domain.project.dto.res;

import java.time.LocalDateTime;

import com.AIMLproject.backend.domain.project.model.Project;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRes {

	private Long projectId;
	private LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
	private Boolean isPublic;
	private String title;
	private String subtitle;

	public ProjectRes(Project project) {
		this.projectId = project.getProjectId();
		this.createdAt = project.getCreatedAt();
		this.lastModifiedAt = project.getLastModifiedAt();
		this.isPublic = project.getIsPublic();
		this.title = project.getTitle();
		this.subtitle = project.getSubtitle();
	}
}
