package org.example.baitap_apispringboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus()).body(
                Map.of("message", errorCode.getMessage(),
                        "status code", errorCode.getStatusCode(),
                        "HttpStatus", errorCode.getHttpStatus())
        );
    }
}
