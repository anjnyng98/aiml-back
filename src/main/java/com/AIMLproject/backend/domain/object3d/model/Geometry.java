package com.AIMLproject.backend.domain.object3d.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Geometry {
	private String type;

	public Geometry(String type) {
		this.type = type;
	}

	public Geometry() {

	}
}
