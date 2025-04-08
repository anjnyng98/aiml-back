package com.AIMLproject.backend.global.s3;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AIMLproject.backend.domain.user.model.User;
import com.AIMLproject.backend.domain.user.service.UserService;

@RestController
@RequestMapping("/api")
public class S3Controller {

	private final UserService userService;
	private final S3Service s3Service;

	@Autowired
	public S3Controller(UserService userService, S3Service s3Service) {
		this.userService = userService;
		this.s3Service = s3Service;
	}

	@PutMapping("/s3/upload")
	// @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Map<String, String>> upload(@AuthenticationPrincipal UserDetails userDetails,
		@RequestBody S3Request req) {

		User user = userService.findUserByUsername(userDetails.getUsername());
		String imageExtension = req.getImageExtension();
		Long contentLength = req.getContentLength();

		/* 1. 이미지 크기 제한이 넘을 경우
		if (ImageUtil.isValidFileSize(contentLength) == false) {
			String message = ErrorResponse.IMAGE_CONTENT_TOO_LARGE.getMessage();
			return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(message);
		}

		2. 이미지 확장자가 허용되지 않은 경우
		if (ImageUtil.isValidImageExtension(imageExtension) == false) {
			String message = ErrorResponse.UNSUPPORTED_IMAGE_EXTENSION.getMessage();
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(message);
		}

		3. 닉네임 규칙이 맞지 않은 경우
		if (memberProfileService.isValidNickname(nickname) == false) {
			String message = ErrorResponse.INVALID_NICKNAME_RULE.getMessage();
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(message);
		}

		4. 닉네임만 변경
		if (imageExtension.isEmpty()) {
			member.updateProfile(nickname, member.getImageFileName());
			memberService.save(member);
			String message = Response.PROFILE_UPDATE_SUCCESS.getMessage();
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} */

		// 5. 프로필 이미지가 이미 존재하고, 새롭게 업로드 하는 경우
		s3Service.deleteFile("profiles", user.getImageFileName());

		String randomFileName = UUID.randomUUID().toString();
		String savedFileName = String.format("%s.%s", randomFileName, imageExtension);

		String preSignedUrl = s3Service.getPutPreSignedUrl("profiles", savedFileName, imageExtension,
			contentLength);

		userService.setImageFileName(user, savedFileName);

		Map<String, String> res = new HashMap<>();
		res.put("preSignedUrl", preSignedUrl);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/s3/load")
	// @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> load(@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findUserByUsername(userDetails.getUsername());
		String imageFileName = user.getImageFileName();

		if (imageFileName == null) {
			return ResponseEntity.status(404).body("File not found");
		}

		String preSignedUrl = s3Service.getGetPreSignedUrl("profiles", imageFileName);

		Map<String, String> res = new HashMap<>();
		res.put("preSignedUrl", preSignedUrl);
		return ResponseEntity.ok(res);
	}

	@DeleteMapping("/s3/delete")
	public ResponseEntity<?> deleteFile(@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findUserByUsername(userDetails.getUsername());

		String imageFileName = user.getImageFileName();

		if (imageFileName == null) {
			return ResponseEntity.status(404).body("File not found");
		}

		s3Service.deleteFile("profiles", imageFileName);
		userService.deleteFile(user);

		return ResponseEntity.ok("File deleted successfully");
	}

}
