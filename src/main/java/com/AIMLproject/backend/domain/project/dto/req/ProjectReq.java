package com.AIMLproject.backend.domain.project.dto.req;

import lombok.Getter;

@Getter
public class ProjectReq {
	private String title;
	private String subtitle;
	private Boolean isPublic;
}
