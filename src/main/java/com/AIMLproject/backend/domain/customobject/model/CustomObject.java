package com.AIMLproject.backend.domain.customobject.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.AIMLproject.backend.domain.project.model.Project;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class CustomObject {

	@Id
	@GeneratedValue
	private Long objectId;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Project project;

	@CreatedDate
	private LocalDateTime createdAt;

	@LastModifiedDate
	private LocalDateTime lastModifiedAt;

	@ElementCollection
	private List<Double> matrix;

	private String geometry; // type

	private String material; // color

	public CustomObject(Project project, List<Double> matrix, String geometry, String material) {
		this.project = project;
		this.matrix = matrix;
		this.geometry = geometry;
		this.material = material;
	}

	public CustomObject() {
	}
}
