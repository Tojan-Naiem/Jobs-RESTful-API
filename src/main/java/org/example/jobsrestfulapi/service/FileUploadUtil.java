package org.example.jobsrestfulapi.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static void saveFile(String uploadDir,String fileName,MultipartFile file) throws IOException {
        Path uploadPath= Paths.get(uploadDir);
        System.out.println("I'm in file upload class");
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        try(InputStream inputStream=file.getInputStream()){
            Path filePath=uploadPath.resolve(fileName);
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("I'm in file upload class inside try");

        }
        catch (IOException e){
            throw new IOException("Could not save image file : "+fileName,e);

        }
    }
}
