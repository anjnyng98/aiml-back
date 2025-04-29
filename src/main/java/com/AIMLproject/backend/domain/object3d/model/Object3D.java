package com.AIMLproject.backend.domain.object3d.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.AIMLproject.backend.global.common.BaseEntity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Object3D extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	protected Object3DType type;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Object3D parent;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "x", column = @Column(name = "position_x")),
		@AttributeOverride(name = "y", column = @Column(name = "position_y")),
		@AttributeOverride(name = "z", column = @Column(name = "position_z"))
	})
	private Vector3 position;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "x", column = @Column(name = "rotation_x")),
		@AttributeOverride(name = "y", column = @Column(name = "rotation_y")),
		@AttributeOverride(name = "z", column = @Column(name = "rotation_z"))
	})
	private Vector3 rotation;

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "x", column = @Column(name = "scale_x")),
		@AttributeOverride(name = "y", column = @Column(name = "scale_y")),
		@AttributeOverride(name = "z", column = @Column(name = "scale_z"))
	})
	private Vector3 scale;

	public Object3D(Object3DType type, Object3D parent, Vector3 position, Vector3 rotation, Vector3 scale) {
		this.type = type;
		this.parent = parent;
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
	}

	public Object3D() {

	}
}
