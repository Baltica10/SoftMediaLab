package com.lab.media.soft.demo.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.*;
import java.util.stream.Collectors;

import static com.lab.media.soft.demo.util.Constants.FULL_DATE_PATTERN;

@Getter
@Setter
public class ErrorMessage {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = FULL_DATE_PATTERN)
    private Calendar timestamp;
    private String message;
    private int code;
    private String type;
    private String trace;

    public ErrorMessage(String message, HttpStatus httpStatus) {
        this.message = message;
        this.code = httpStatus.value();
        this.type = httpStatus.getReasonPhrase();
        this.timestamp = Calendar.getInstance();
    }

    public ErrorMessage(String message, HttpStatus httpStatus, List<FieldError> errors) {
        this(message, httpStatus);
        this.trace = errors.stream()
                .map(error -> String.format("%s: %s", error.getField(), error.getDefaultMessage()))
                .collect(Collectors.joining("\n "));
    }
}