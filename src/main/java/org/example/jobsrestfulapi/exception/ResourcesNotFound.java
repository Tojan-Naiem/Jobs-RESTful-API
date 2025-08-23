package org.example.joblisting.exceptions;

public class ResourcesNotFound extends RuntimeException{
    public ResourcesNotFound(String msg){
        super(msg);
    }
}
