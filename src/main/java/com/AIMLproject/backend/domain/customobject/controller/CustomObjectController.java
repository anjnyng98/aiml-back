package com.AIMLproject.backend.domain.customobject.controller;

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

import com.AIMLproject.backend.domain.customobject.model.CustomObject;
import com.AIMLproject.backend.domain.user.model.User;
import com.AIMLproject.backend.domain.customobject.dto.req.CustomObjectReq;
import com.AIMLproject.backend.domain.customobject.dto.res.CustomObjectRes;
import com.AIMLproject.backend.domain.customobject.service.CustomObjectService;
import com.AIMLproject.backend.domain.project.service.ProjectService;
import com.AIMLproject.backend.domain.user.service.UserService;

@RestController
@RequestMapping("/api")
public class CustomObjectController {

	private final UserService userService;
	private final ProjectService projectService;
	private final CustomObjectService customObjectService;

	@Autowired
	public CustomObjectController(UserService userService, ProjectService projectService, CustomObjectService customObjectService) {
		this.userService = userService;
		this.projectService = projectService;
		this.customObjectService = customObjectService;
	}

	@PostMapping("/projects/{projectId}/objects")
	public ResponseEntity<CustomObjectRes> createObject(@AuthenticationPrincipal UserDetails userDetails,
		@PathVariable Long projectId, @RequestBody CustomObjectReq req) {
		User user = userService.findUserByUsername(userDetails.getUsername());
		CustomObject newObject = customObjectService.createObject(user, projectId, req.getMatrix(), req.getGeometry(),
			req.getMaterial());
		CustomObjectRes res = new CustomObjectRes(newObject);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/objects/{objectId}") // *******************************************************
	public ResponseEntity<CustomObjectRes> afs(@AuthenticationPrincipal UserDetails userDetails,
		@PathVariable Long objectId) {
		User user = (userDetails != null) ? userService.findUserByUsername(userDetails.getUsername()) : null;
		CustomObject object = customObjectService.getPublicObject(user, objectId);
		CustomObjectRes res = new CustomObjectRes(object);
		return ResponseEntity.ok(res);
	}

	@PutMapping("/objects/{objectId}")
	public ResponseEntity<CustomObjectRes> updateObject(@AuthenticationPrincipal UserDetails userDetails,
		@PathVariable Long objectId, @RequestBody CustomObjectReq req) {
		User user = userService.findUserByUsername(userDetails.getUsername());
		CustomObject updatedObject = customObjectService.updateObject(user, objectId, req.getMatrix(), req.getGeometry(),
			req.getMaterial());
		CustomObjectRes res = new CustomObjectRes(updatedObject);
		return ResponseEntity.ok(res);
	}

	@DeleteMapping("/objects/{objectId}")
	public ResponseEntity<Void> method(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long objectId) {
		User user = userService.findUserByUsername(userDetails.getUsername());
		customObjectService.deleteObject(user, objectId);
		return ResponseEntity.ok().build();
	}
}
