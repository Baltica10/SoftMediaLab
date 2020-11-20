package com.lab.media.soft.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends CommonException {

    public EntityNotFoundException(String message) {
        super(message, null);
    }
}