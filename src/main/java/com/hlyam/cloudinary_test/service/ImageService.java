package com.hlyam.cloudinary_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hlyam.cloudinary_test.storage.StorageService;

@Service
public class ImageService {
	@Autowired
	@Qualifier("cloudinaryStorageService")
	private StorageService storageService;

    public String uploadImage(MultipartFile file) {

        String filename = storageService.store(file);
        return filename;
    }
}
