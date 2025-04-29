package com.AIMLproject.backend.domain.object3d.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AIMLproject.backend.domain.object3d.model.Scene;

public interface SceneRepository extends JpaRepository<Scene, Long> {
	Optional<Scene> findByProject_Id(Long projectId);
}
