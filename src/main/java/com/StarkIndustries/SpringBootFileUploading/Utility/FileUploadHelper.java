package com.StarkIndustries.SpringBootFileUploading.Utility;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    public static final String FILE_UPLOAD_DIRECTORY="C:\\Users\\Aditya\\Desktop\\Programing files all\\Java Backend\\Spring-Boot\\SpringBoot_Api\\SpringBootFileUploading\\src\\main\\resources\\static\\Images";

    public boolean uploadFile(MultipartFile file){

        boolean status = false;
        try{

//            InputStream inputStream = file.getInputStream();
//            byte byteData[] = new byte[inputStream.available()];
//            inputStream.read(byteData);
//
//            FileOutputStream fileOutputStream = new FileOutputStream(FILE_UPLOAD_DIRECTORY+ File.separator+file.getOriginalFilename());
//            fileOutputStream.write(byteData);
//            fileOutputStream.flush();
//            fileOutputStream.close();
//            inputStream.close();

            Files.copy(file.getInputStream(), Paths.get(FILE_UPLOAD_DIRECTORY+ File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            status=true;

        }catch (Exception e){
            e.printStackTrace();
        }
        return status;
    }
}