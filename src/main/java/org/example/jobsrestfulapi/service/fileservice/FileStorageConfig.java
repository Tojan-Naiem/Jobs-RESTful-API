package org.example.jobsrestfulapi.service.fileservice;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStorageConfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        exposeDirectory("company-images",registry);

    }
    private void exposeDirectory(String dirName,ResourceHandlerRegistry registry){
        Path uploadDir= Paths.get(dirName);
        String uploadPath=uploadDir.toFile().getAbsolutePath();
        if(dirName.startsWith("../"))
            dirName.replace("../","");
        registry.addResourceHandler("/"+dirName+"/**").addResourceLocations("file:"+uploadPath+"/");
    }
}
