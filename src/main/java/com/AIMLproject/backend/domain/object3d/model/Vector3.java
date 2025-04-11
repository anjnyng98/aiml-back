package com.AIMLproject.backend.domain.object3d.model;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Vector3 {

	private float x;
	private float y;
	private float z;

	public Vector3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3() {

	}
}
