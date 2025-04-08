package com.AIMLproject.backend.domain.customobject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AIMLproject.backend.domain.customobject.model.Mesh;
import com.AIMLproject.backend.domain.project.model.Project;

public interface MeshRepository extends JpaRepository<Mesh, Long> {
	List<Mesh> findByProject(Project project);
}
