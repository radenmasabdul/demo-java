package com.example.demo.exception;

import com.example.demo.dto.ApiResponse;
import com.example.demo.util.ResponseHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(AppException.class)
  public ResponseEntity<ApiResponse<Void>> handleAppException(AppException exception) {
    return ResponseHandler.buildError(
      exception.getStatus(),
      exception.getMessage()
    );
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse<Void>> handleGeneralException(Exception exception) {
    return ResponseHandler.internalServerError(
      "Internal server error"
    );
  }
}
