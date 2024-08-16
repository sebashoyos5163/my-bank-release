package com.accenture.customers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND)
public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String resourceName,  String filedName , String filedValue) {
        super(String.format("No se encontro un %s con el valor %s en el campo %s",
                resourceName,filedName,filedValue));
    }
}
