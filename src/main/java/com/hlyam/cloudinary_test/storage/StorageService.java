package com.hlyam.cloudinary_test.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    String store(MultipartFile file,String folderName);
    Object delete(String filename);
    String update(MultipartFile newFile, String publicId, String folderName);
}
