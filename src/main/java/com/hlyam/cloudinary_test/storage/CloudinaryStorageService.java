package com.hlyam.cloudinary_test.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
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
    public String store(MultipartFile file) {
        try {
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            return (String) result.get("secure_url"); // Or use public_id if you prefer
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload to Cloudinary", e);
        }
    }

    @Override
    public Resource loadAsResource(String filenameOrUrl) {
        try {
            return new UrlResource(URI.create(filenameOrUrl).toURL());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Cloudinary URL", e);
        }
    }

    // These may be optional or unsupported for remote storage
    @Override public void delete(String publicId) {
        try {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete from Cloudinary", e);
        }
    }

    @Override public List<Path> loadAll() {
        throw new UnsupportedOperationException("loadAll not supported for Cloudinary");
    }

    @Override public Path load(String filename) {
        throw new UnsupportedOperationException("load not supported for Cloudinary");
    }

    @Override public void deleteAll() {
        throw new UnsupportedOperationException("deleteAll not supported for Cloudinary");
    }

    @Override public void init() {
        // No init required
    }
}

