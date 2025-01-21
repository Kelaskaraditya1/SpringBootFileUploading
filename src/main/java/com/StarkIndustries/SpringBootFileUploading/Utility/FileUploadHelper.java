package com.StarkIndustries.SpringBootFileUploading.Utility;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUploadHelper {

    public FileUploadHelper() throws IOException{

    }

    public static  String FILE_UPLOAD_DIRECTORY="";


    //            InputStream inputStream = file.getInputStream();
//            byte byteData[] = new byte[inputStream.available()];
//            inputStream.read(byteData);
//
//            FileOutputStream fileOutputStream = new FileOutputStream(FILE_UPLOAD_DIRECTORY+ File.separator+file.getOriginalFilename());
//            fileOutputStream.write(byteData);
//            fileOutputStream.flush();
//            fileOutputStream.close();
//            inputStream.close();

    // With Static Path
//            Files.copy(file.getInputStream(), Paths.get(FILE_UPLOAD_DIRECTORY+ File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

    public boolean uploadFile(MultipartFile file) {
        boolean status = false;

        try {
            // Define static directory path relative to the project
            String DYNAMIC_FILE_PATH = Paths.get("src/main/resources/static/Images").toAbsolutePath().toString();

            // Ensure the directory exists
            Path uploadDirPath = Paths.get(DYNAMIC_FILE_PATH);
            if (!Files.exists(uploadDirPath)) {
                Files.createDirectories(uploadDirPath); // Create the directory if it doesn't exist
            }

            // Copy the file to the directory
            Files.copy(
                    file.getInputStream(),
                    uploadDirPath.resolve(file.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING
            );

            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

}