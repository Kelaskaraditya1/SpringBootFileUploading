package com.StarkIndustries.SpringBootFileUploading.Cloudinary.Service;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class CloudinaryUploadService {

    @Autowired
    public Cloudinary cloudinary;

    public Map uploadImage(MultipartFile multipartFile){

        try{
            Map data = this.cloudinary.uploader().upload(multipartFile.getBytes(),Map.of());
            return data;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
