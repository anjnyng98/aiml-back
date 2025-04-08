package com.AIMLproject.backend.domain.project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.AIMLproject.backend.domain.project.model.Project;
import com.AIMLproject.backend.domain.user.model.User;

public interface ProjectRepository extends JpaRepository<Project, Long> {
	List<Project> findByUser(User user);

	Page<Project> findByIsPublicTrueAndTitleContainingIgnoreCase(String keyword, Pageable pageable);
}
