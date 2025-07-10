package com.hlyam.cloudinary_test.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hlyam.cloudinary_test.service.ImageService;


@RestController
@RequestMapping("/upload")
public class ImageUploadController {
	
	@Autowired
	private ImageService imageService;

	@PostMapping
	public ResponseEntity<?> uploadImage(
		    @RequestParam("file") final MultipartFile file
		) {
		    try {
		        String fileUrl = this.imageService.uploadImage(file);
		        return ResponseEntity.ok(Map.of("url", fileUrl));
		    } catch (Exception e) {
		        return ResponseEntity.badRequest().body("Failed to upload file: " + e.getMessage());
		    }
		}
}
