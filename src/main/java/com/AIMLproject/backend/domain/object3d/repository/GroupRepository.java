package com.AIMLproject.backend.domain.object3d.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AIMLproject.backend.domain.object3d.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
