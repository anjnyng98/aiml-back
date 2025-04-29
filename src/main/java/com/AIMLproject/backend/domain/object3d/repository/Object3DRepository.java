package com.AIMLproject.backend.domain.object3d.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AIMLproject.backend.domain.object3d.model.Object3D;

public interface Object3DRepository extends JpaRepository<Object3D, Long> {
	List<Object3D> findByParent_Id(Long parentId);
}
