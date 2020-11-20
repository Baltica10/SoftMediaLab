package com.lab.media.soft.demo.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ValidationException extends CommonException {
    private List<FieldError> errors;

    public ValidationException(String message) {
        super(message, null);
    }

    public ValidationException(String message, List<FieldError> errors) {
        super(message, null);
        this.errors = errors;
    }
}