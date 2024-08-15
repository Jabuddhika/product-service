package com.efutures.products.service.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException{

    private final HttpStatus status;
    public CustomException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
    public CustomException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.status = status;
    }
    public CustomException(HttpStatus status, Throwable cause) {
        super(status.getReasonPhrase(), cause);
        this.status = status;
    }

}
