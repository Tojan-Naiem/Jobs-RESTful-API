package org.example.jobsrestfulapi.exception;

public class ResourcesAlreadyFound extends RuntimeException{
    public ResourcesAlreadyFound(String msg){
        super(msg);
    }
}
