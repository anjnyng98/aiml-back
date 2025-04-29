package com.AIMLproject.backend.domain.object3d.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Light extends Object3D {

	private int color;
	private double intensity;

	public Light(Object3D parent, Vector3 position, Vector3 rotation, Vector3 scale, int color, double intensity) {
		super(Object3DType.LIGHT, parent, position, rotation, scale);
		this.color = color;
		this.intensity = intensity;
	}

	public Light() {
		super();
	}
}
