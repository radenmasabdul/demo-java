package com.example.demo.controller;

import com.example.demo.dto.ApiResponse;
import com.example.demo.entity.User;
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
  public ResponseEntity<ApiResponse<List<User>>> getAllUsers(@RequestParam(required = false) String search) {
    List<User> users = userService.getAllUsers(search);
    return ResponseHandler.ok("User retrieved successfully", users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable String id) {
    User user = userService.getUserById(id);
    return ResponseHandler.ok("User details found", user);
  }

  @PostMapping
  public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
    User savedUser = userService.createUser(user);
    return ResponseHandler.created("New user successfully added", savedUser);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable String id, @RequestBody User userDetails) {
    User updatedUser = userService.updateUser(id, userDetails);
    return ResponseHandler.ok("User data successfully updated", updatedUser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    userService.deleteUser(id);
    return ResponseHandler.noContent();
  }
}