package com.AIMLproject.backend.domain.customobject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AIMLproject.backend.domain.customobject.model.CustomObject;
import com.AIMLproject.backend.domain.project.model.Project;

public interface ObjectRepository extends JpaRepository<CustomObject, Long> {
	List<CustomObject> findByProject(Project project);

	Optional<Project> findProjectByObjectId(Long objectId);

	Optional<CustomObject> findByProjectAndObjectId(Project project, Long objectId);
}
