package com.lab.media.soft.demo.exception;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final MediaType CONTENT_TYPE = MediaType.APPLICATION_JSON;

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<CustomException> handleNotFoundException(EntityNotFoundException ex) {
        logException(ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(CONTENT_TYPE)
                .body(new CustomException(ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<ErrorMessage> handleValidationException(ValidationException ex) {
        logException(ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(CONTENT_TYPE)
                .body(new ErrorMessage(ex.getMessage(), HttpStatus.BAD_REQUEST, ex.getErrors()));
    }

    private void logException(@NotNull Exception ex) {
        log.warn("Exception message: {}", ex.getMessage());
        log.debug("Trace: ", ex);
    }

    @Data
    @AllArgsConstructor
    private static class CustomException {
        private String message;
        private int status;
    }
}