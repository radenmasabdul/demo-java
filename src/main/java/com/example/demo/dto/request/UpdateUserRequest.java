package com.example.demo.dto.request;

import com.example.demo.entity.Role;
import com.example.demo.entity.Status;

import jakarta.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

  private Role role;
  private Status status;

  @Pattern(regexp = "^\\+?[0-9]{8,15}$", message = "Phone number format is invalid")
  private String phoneNumber;
  private String profileImageUrl;
}