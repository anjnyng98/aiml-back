package com.AIMLproject.backend.global.s3;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
public class S3Service {

	private final S3Client s3Client;
	private final S3Presigner preSigner;

	@Value("${spring.cloud.aws.s3.bucket}")
	private String bucket;

	@Autowired
	public S3Service(S3Client s3Client, S3Presigner preSigner) {
		this.s3Client = s3Client;
		this.preSigner = preSigner;
	}

	public String getPutPreSignedUrl(String prefix, String fileName, String extension, Long contentLength) {

		String filePath = String.format("%s/%s", prefix, fileName);
		String contentType = String.format("image/%s", extension);

		PutObjectRequest objectRequest = PutObjectRequest.builder()
			.bucket(bucket)
			.key(filePath)
			.contentType(contentType)
			.contentLength(contentLength)
			.build();

		PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
			.signatureDuration(Duration.ofMinutes(10))
			.putObjectRequest(objectRequest)
			.build();

		PresignedPutObjectRequest presignedRequest = preSigner.presignPutObject(presignRequest);

		return presignedRequest.url().toString();
	}

	public String getGetPreSignedUrl(String prefix, String fileName) {
		String filePath = prefix + "/" + fileName;
		GetObjectRequest objectRequest = GetObjectRequest.builder()
			.bucket(bucket)
			.key(filePath)
			.build();

		GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
			.signatureDuration(Duration.ofMinutes(10))
			.getObjectRequest(objectRequest)
			.build();

		PresignedGetObjectRequest presignedRequest = preSigner.presignGetObject(presignRequest);
		return presignedRequest.url().toExternalForm();
	}

	public void deleteFile(String prefix, String fileName) {
		if (fileName != null) {
			String filePath = prefix + "/" + fileName;
			s3Client.deleteObject(DeleteObjectRequest.builder()
				.bucket(bucket)
				.key(filePath)
				.build());
		}
	}
}
