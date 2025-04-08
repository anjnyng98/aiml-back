package com.AIMLproject.backend.domain.userproject.dto.req;

import lombok.Getter;

@Getter
public class InviteReq {
	private Long projectId;
	private Long userId;
	private Boolean readOnly;
}
