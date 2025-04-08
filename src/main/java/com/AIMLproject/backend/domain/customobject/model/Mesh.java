package com.AIMLproject.backend.domain.customobject.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.AIMLproject.backend.domain.project.model.Project;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
public class Mesh {

	@Id
	@GeneratedValue
	private Long objectId;

	// Audit // to do: how to audit CreatedBy and LastModifiedBy
	@CreatedDate
	private LocalDateTime createdAt;
	@LastModifiedDate
	private LocalDateTime lastModifiedAt;
	// @CreatedBy
	// private Long createdBy;
	// @LastModifiedBy
	// private Long lastModifiedBy;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.REMOVE)
	private Project project;

	// Info
	@ElementCollection
	private List<Double> matrix;
	private String geometry; // type
	private String material; // color

	public Mesh(Project project, List<Double> matrix, String geometry, String material) {
		this.project = project;
		this.matrix = matrix;
		this.geometry = geometry;
		this.material = material;
	}

	public Mesh() {

	}
}
