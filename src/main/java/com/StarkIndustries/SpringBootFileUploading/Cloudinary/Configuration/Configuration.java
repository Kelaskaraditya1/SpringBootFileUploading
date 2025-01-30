package com.StarkIndustries.SpringBootFileUploading.Cloudinary.Configuration;

import com.StarkIndustries.SpringBootFileUploading.Cloudinary.Keys.Keys;
import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@org.springframework.context.annotation.Configuration
public class Configuration{

    @Value("${api.key}")
    private String API_KEY;

    @Value("${api.secrete}")
    private String API_SECRETE;

    @Bean
    public Cloudinary getCloudinary(){

        Map<String, Object> properties = new HashMap<>();
        properties.put(Keys.CLOUD_NAME,"dhdrzsxor");
        properties.put(Keys.API_KEY,API_KEY);
        properties.put(Keys.API_SECRETE,API_SECRETE);
        properties.put(Keys.SECURE,true);

        return new Cloudinary(properties);
    }
}