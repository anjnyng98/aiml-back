package com.AIMLproject.backend.domain.customobject.dto.res;

import java.time.LocalDateTime;
import java.util.List;

import com.AIMLproject.backend.domain.customobject.model.CustomObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomObjectRes {

	private Long objectId;

	private LocalDateTime createdAt;
	private LocalDateTime lastModifiedAt;
	// private Long createdBy;
	// private Long lastModifiedBy;

	private List<Double> matrix;
	private String geometry; // type
	private String material; // color

	public CustomObjectRes(CustomObject object) {
		this.objectId = object.getObjectId();
		this.createdAt = object.getCreatedAt();
		this.lastModifiedAt = object.getLastModifiedAt();
		// this.createdBy = object.getCreatedBy();
		// this.lastModifiedBy = object.getLastModifiedBy();
		this.matrix = object.getMatrix();
		this.geometry = object.getGeometry();
		this.material = object.getMaterial();
	}

}
