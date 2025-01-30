package com.StarkIndustries.SpringBootFileUploading.Controller;

import ch.qos.logback.core.util.FileSize;
import com.StarkIndustries.SpringBootFileUploading.Cloudinary.Service.CloudinaryUploadService;
import com.StarkIndustries.SpringBootFileUploading.Utility.FileUploadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Map;

@RestController
public class Controller {

    @Autowired
    public FileUploadHelper fileUploadHelper;

    @Autowired
    public CloudinaryUploadService cloudinaryUploadService;

    @GetMapping("/greetings")
    public String greetings(){
        return "Greetings!!\nI am Optimus Prime";
    }

//    @PostMapping("/upload-file")
//    public ResponseEntity<String> uploadFile(@RequestParam("image")MultipartFile file){
//
//        if(file.isEmpty())
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No File Selected");
//        else{
//            if(!file.getContentType().equals("image/jpeg"))
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only Jpeg Images are allowed ");
//        }
//        fileUploadHelper.uploadFile(file);
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body("File "+file.getOriginalFilename()+" Uploaded Successfully");
//    }


    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFileWithDynamicPath(@RequestParam("image") MultipartFile file){
        if(file.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No File Selected!!");
        else{
            if(!file.getContentType().equals("image/jpeg"))
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Supports only Jpeg format!!");
            fileUploadHelper.uploadFile(file);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(ServletUriComponentsBuilder.fromCurrentContextPath().path("/Images/").path(file.getOriginalFilename()).toUriString());
        }
    }


    @PostMapping("/upload-image")
    public ResponseEntity<Map> uploadImage(@RequestParam("image")MultipartFile file){
        Map map = this.cloudinaryUploadService.uploadImage(file);
        if(map!=null){
            System.out.println(map.get("secure_url"));
            return ResponseEntity.status(HttpStatus.OK).body(map);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }
}
