package com.AIMLproject.backend.domain.object3d.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`group`")
public class Group extends Object3D {

	public Group(Object3D parent, Vector3 position, Vector3 rotation, Vector3 scale) {
		super(Object3DType.GROUP, parent, position, rotation, scale);
	}

	public Group() {
		super();
	}
}
