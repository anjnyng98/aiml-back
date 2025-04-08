package com.AIMLproject.backend.domain.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AIMLproject.backend.domain.project.model.Project;
import com.AIMLproject.backend.domain.user.model.User;
import com.AIMLproject.backend.domain.userproject.dto.req.InviteReq;
import com.AIMLproject.backend.domain.user.dto.req.UserReq;
import com.AIMLproject.backend.domain.project.dto.res.ProjectRes;
import com.AIMLproject.backend.domain.user.dto.res.UserRes;
import com.AIMLproject.backend.domain.project.service.ProjectService;
import com.AIMLproject.backend.domain.user.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	private final UserService userService;
	private final ProjectService projectService;

	@Autowired
	public UserController(UserService userService, ProjectService projectService) {
		this.userService = userService;
		this.projectService = projectService;
	}

	@PostMapping("/users")
	public ResponseEntity<UserRes> create(@RequestBody UserReq req) {
		User user = userService.createUser(req.getUsername(), req.getPassword(), req.getFirstName(), req.getLastName(),
			req.getEmail());
		UserRes res = new UserRes(user);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/users/me")
	public ResponseEntity<UserRes> read(@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findUserByUsername(userDetails.getUsername());
		UserRes res = new UserRes(user);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserRes> getUser(@PathVariable Long userId) {
		User user = userService.readUser(userId);
		UserRes res = new UserRes(user);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/users/me/projects") // to do
	public ResponseEntity<Map<String, List<ProjectRes>>> newReadUserProjects(
		@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findUserByUsername(userDetails.getUsername());
		List<Project> projects = projectService.findAllProjectsByUser(user);
		List<ProjectRes> res = projects.stream().map(ProjectRes::new).collect(Collectors.toList());
		Map<String, List<ProjectRes>> newRes = new HashMap<>();
		newRes.put("projects", res);
		return ResponseEntity.ok(newRes);
	}

	@PutMapping("/users/me")
	public ResponseEntity<UserRes> putUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserReq req) {
		User user = userService.findUserByUsername(userDetails.getUsername());
		User updatedUser = userService.updateUser(user, req.getFirstName(), req.getLastName(), req.getEmail());
		UserRes res = new UserRes(updatedUser);
		return ResponseEntity.ok(res);
	}

	@DeleteMapping("/users/me")
	public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findUserByUsername(userDetails.getUsername());
		userService.deleteUser(user);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/users/invite")
	public ResponseEntity<Void> invite(@AuthenticationPrincipal UserDetails userDetails, @RequestBody InviteReq req) {
		// sender, reciever, projectId, userId;
		User invitor = userService.findUserByUsername(userDetails.getUsername());
		projectService.inviteUser(invitor, req.getProjectId(), req.getUserId(), req.getReadOnly());
		return ResponseEntity.ok().build();
	}
}
