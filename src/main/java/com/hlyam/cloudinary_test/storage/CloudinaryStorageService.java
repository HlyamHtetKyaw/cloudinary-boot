package com.hlyam.cloudinary_test.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service("cloudinaryStorageService")
public class CloudinaryStorageService implements StorageService {

    private final Cloudinary cloudinary;

    public CloudinaryStorageService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String store(MultipartFile file,String folderName) {
        try {
        	HashMap<Object, Object> options = new HashMap<>();
        	 options.put("folder", folderName);
        	 @SuppressWarnings("unchecked")
        	 Map<String, Object> uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
        	 String publicId = (String) uploadedFile.get("public_id");
        	 return cloudinary.url().secure(true).generate(publicId);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload to Cloudinary", e);
        }
    }

    @Override
    public Object delete(String publicId) {
        try {
        	Map<String, Object> deleteOptions = new HashMap<>();
        	deleteOptions.put("invalidate", true);
        	@SuppressWarnings("unchecked")
        	Map<String, Object> result = (Map<String, Object>) cloudinary.uploader()
        	    .destroy(publicId, deleteOptions);
            return result.get("result");
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete from Cloudinary", e);
        }
    }
    
    @Override
    public String update(MultipartFile newFile, String publicId, String folderName) {
        try {
            Map<String, Object> options = new HashMap<>();
            options.put("public_id", folderName + "/" + publicId);
            options.put("overwrite", true);

            @SuppressWarnings("unchecked")
            Map<String, Object> uploadedFile = (Map<String, Object>)
                    cloudinary.uploader().upload(newFile.getBytes(), options);
            return (String) uploadedFile.get("secure_url");
        } catch (IOException e) {
            throw new RuntimeException("Failed to update image on Cloudinary", e);
        }
    }

}

