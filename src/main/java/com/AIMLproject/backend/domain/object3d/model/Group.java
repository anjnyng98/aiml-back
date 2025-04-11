package com.AIMLproject.backend.domain.object3d.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Group extends Object3D {

	public Group(Object3D parent, Vector3 position, Vector3 rotation, Vector3 scale) {
		super(parent, position, rotation, scale);
		this.type = Object3DType.GROUP;
	}

	public Group() {
		super();
	}
}
