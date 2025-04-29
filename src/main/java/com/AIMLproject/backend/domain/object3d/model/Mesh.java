package com.AIMLproject.backend.domain.object3d.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Mesh extends Object3D {

	@Embedded
	private Geometry geometry;

	@Embedded
	private Material material;

	public Mesh(Object3D parent, Vector3 position, Vector3 rotation, Vector3 scale, Geometry geometry,
		Material material) {
		super(Object3DType.MESH, parent, position, rotation, scale);
		this.geometry = geometry;
		this.material = material;
	}

	public Mesh() {
		super();
	}
}
