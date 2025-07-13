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
        return storageService.store(file,"test_folder");
    }
    
    public Object deleteImage(String publicId) {
    	return storageService.delete(publicId);
    }
    
    public String updateImage(MultipartFile newFile, String publicId) {
    	return storageService.update(newFile, publicId, "test_folder");
    }
}
