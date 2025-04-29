package com.AIMLproject.backend.domain.object3d.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AIMLproject.backend.domain.object3d.model.Light;

public interface LightRepository extends JpaRepository<Light, Long> {
}
