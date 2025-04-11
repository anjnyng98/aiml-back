package com.AIMLproject.backend.domain.object3d.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.AIMLproject.backend.domain.project.model.Project;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Camera extends Object3D {

	@OneToOne
	@JoinColumn(name = "project_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	Project project;

	private float fov;
	private float zoom;
	private float near;
	private float far;
	private float aspect;

	public Camera(Project project, Object3D parent, Vector3 position, Vector3 rotation, Vector3 scale, float fov, float zoom, float near, float far, float aspect) {
		super(parent, position, rotation, scale);
		this.type = Object3DType.CAMERA;
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
