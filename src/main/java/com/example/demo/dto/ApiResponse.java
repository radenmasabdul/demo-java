package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder 
public class ApiResponse<T> {
  private int status;
  private String message;
  private T data;
  private LocalDateTime timestamp;
}
