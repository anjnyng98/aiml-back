package com.AIMLproject.backend.domain.object3d.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.AIMLproject.backend.domain.project.model.Project;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Camera extends Object3D {

	@OneToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	Project project;

	private double fov;
	private double zoom;
	private double near;
	private double far;
	private double aspect;

	public Camera(Project project, Object3D parent, Vector3 position, Vector3 rotation, Vector3 scale, double fov,
		double zoom, double near, double far, double aspect) {
		super(Object3DType.CAMERA, parent, position, rotation, scale);
		this.project = project;
		this.fov = fov;
		this.zoom = zoom;
		this.near = near;
		this.far = far;
		this.aspect = aspect;
	}

	public Camera() {
		super();
	}
}
