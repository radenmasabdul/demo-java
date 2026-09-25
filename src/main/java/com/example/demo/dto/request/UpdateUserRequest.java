package com.example.demo.dto.request;

import com.example.demo.entity.Role;
import com.example.demo.entity.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {

  private Role role;
  private Status status;
  private String phoneNumber;
  private String profileImageUrl;
}