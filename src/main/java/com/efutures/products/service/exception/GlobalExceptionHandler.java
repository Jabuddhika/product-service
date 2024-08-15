package com.efutures.products.service.exception;

import com.efutures.products.service.dto.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // handle custom exceptions
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionDTO> handleCustomException(CustomException ex) {
        return buildExceptionResponse(ex.getStatus(), ex.getMessage(), null);
    }

    // handle hibernate validation exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDTO> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, Object> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return buildExceptionResponse(HttpStatus.BAD_REQUEST, "Validation failed", errors);
    }

    // handle other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> handleGeneralException(Exception ex) {
        Map<String, Object> data = Map.of("exception", ex.getMessage());
        return buildExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", data);
    }

    private ResponseEntity<ExceptionDTO> buildExceptionResponse(HttpStatus status, String message, Map<String, Object> data) {
        ExceptionDTO response = ExceptionDTO.builder()
                .errorCode(status.value())
                .errorMessage(message)
                .data(data)
                .build();
        return new ResponseEntity<>(response, status);
    }
}
