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
public class Scene extends Object3D {

	@OneToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	Project project;

	private String background;

	public Scene(Project project, Object3D parent, Vector3 position, Vector3 rotation, Vector3 scale,
		String background) {
		super(Object3DType.SCENE, parent, position, rotation, scale);
		this.project = project;
		this.background = background;
	}

	public Scene() {
		super();
	}
}
