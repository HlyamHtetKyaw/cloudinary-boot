package com.hlyam.cloudinary_test.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hlyam.cloudinary_test.service.ImageService;

@RestController
@RequestMapping("/image")
public class ImageUploadController {

	@Autowired
	private ImageService imageService;

	@PostMapping("/upload")
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
		try {
			String fileUrl = this.imageService.uploadImage(file);
			return ResponseEntity.ok(Map.of("url", fileUrl));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to upload file: " + e.getMessage());
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteImage(@RequestParam("publicId") String publicId) {
		return ResponseEntity.ok(Map.of("result",imageService.deleteImage(publicId)));
	}

	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestParam("file") MultipartFile file,
			@RequestParam("publicId") String publicId) {
		try {
			String fileUrl = this.imageService.updateImage(file, publicId);
			return ResponseEntity.ok(Map.of("url", fileUrl));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to update file: " + e.getMessage());
		}
	}

}
