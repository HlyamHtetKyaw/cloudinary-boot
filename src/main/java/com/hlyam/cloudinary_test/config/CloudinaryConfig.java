package com.hlyam.cloudinary_test.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class CloudinaryConfig {
	private Dotenv dotenv = Dotenv.configure().ignoreIfMissing().systemProperties().load();

    private String cloudName = dotenv.get("CLOUDINARY_CLOUD_NAME");

    private String apiKey = dotenv.get("CLOUDINARY_API_KEY");

    private String apiSecret = dotenv.get("CLOUDINARY_API_SECRET");

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_key", apiKey);
        config.put("api_secret", apiSecret);
        return new Cloudinary(config);
    }
}
