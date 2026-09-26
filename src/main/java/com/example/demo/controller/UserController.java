package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.request.UpdateUserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.service.UserService;
import com.example.demo.util.ResponseHandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers(@RequestParam(required = false) String search) {
    return ResponseHandler.ok("User retrieved successfully", userService.getAllUsers(search));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable String id) {
    return ResponseHandler.ok("User details found", userService.getUserById(id));
  }

  @PostMapping
  public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody CreateUserRequest request) {
    return ResponseHandler.created("New user successfully added", userService.createUser(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable String id, @RequestBody UpdateUserRequest request) {
    return ResponseHandler.ok("User data successfully updated", userService.updateUser(id, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    userService.deleteUser(id);
    return ResponseHandler.noContent();
  }
}