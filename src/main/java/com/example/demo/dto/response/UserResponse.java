package com.example.demo.dto.response;

import com.example.demo.entity.Role;
import com.example.demo.entity.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

  private String id;
  private String username;
  private String email;
  private String name;
  private Role role;
  private Status status;
  private String phoneNumber;
  private String profileImageUrl;
}