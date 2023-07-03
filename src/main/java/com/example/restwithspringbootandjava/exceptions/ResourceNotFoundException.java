package com.example.restwithspringbootandjava.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



//Com a anotation ResponseStatus, sempre que ocorrer uma UnsuppoertedMathOperation, será retornado
// um response status com o codigo definido.
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String ex){
        super(ex);
    }


}
