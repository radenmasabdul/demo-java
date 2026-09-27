package com.example.demo.dto.request;

import com.example.demo.entity.Role;
import com.example.demo.entity.Status;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

  @NotBlank(message = "Username is required")
  @Size(min = 3, max = 50, message = "Username must be between 3 and 25 characters")
  private String username;

  @NotBlank(message = "Email is required")
  @Email(message = "Email format is invalid")
  private String email;

  @NotBlank(message = "Name is required")
  private String name;

  @NotNull(message = "Role is required")
  private Role role;

  @NotNull(message = "Status is required")
  private Status status;
  
  private String phoneNumber;
  private String profileImageUrl;
}
