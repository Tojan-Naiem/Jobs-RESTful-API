package org.example.jobsrestfulapi.exception;

public class ResourcesNotFound extends RuntimeException{
    public ResourcesNotFound(String msg){
        super(msg);
    }
}
