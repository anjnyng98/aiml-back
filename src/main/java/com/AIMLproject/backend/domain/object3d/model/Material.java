package com.AIMLproject.backend.domain.object3d.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Material {

	private String color;

	public Material(String color) {
		this.color = color;
	}

	public Material() {

	}
}
