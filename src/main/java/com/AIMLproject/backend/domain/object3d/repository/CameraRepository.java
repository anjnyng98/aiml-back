package com.AIMLproject.backend.domain.object3d.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AIMLproject.backend.domain.object3d.model.Camera;

public interface CameraRepository extends JpaRepository<Camera, Long> {
}
