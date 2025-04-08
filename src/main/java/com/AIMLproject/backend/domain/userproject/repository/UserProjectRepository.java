package com.AIMLproject.backend.domain.userproject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AIMLproject.backend.domain.project.model.Project;
import com.AIMLproject.backend.domain.user.model.User;
import com.AIMLproject.backend.domain.userproject.model.UserProject;

public interface UserProjectRepository extends JpaRepository<UserProject, Long> {
	List<UserProject> findByUser(User user);

	List<UserProject> findByProject(Project project);

	Boolean existsByUserAndProject(User user, Project project);

	Optional<UserProject> findByUserAndProject(User user, Project project);

//	Optional<UserProject> findByUserAndProjectId(User user, Long projectId);
}
