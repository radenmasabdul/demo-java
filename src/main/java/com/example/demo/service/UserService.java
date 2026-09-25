package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.AppException;
import com.example.demo.repository.UserRepository;
import com.example.demo.specification.GenericSpecification;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public List<User> getAllUsers(String search) {
    List<String> columnToSearch = Arrays.asList("name", "email");
    return userRepository.findAll(GenericSpecification.searchByColumn(search, columnToSearch));
  }

  public User getUserById(String id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "User with ID " + id + " was not found")
    );
  }

  public User updateUser(String id, User userDetails) {
    return userRepository.findById(id)
      .map(user -> {
        user.setRole(userDetails.getRole());
        user.setStatus(userDetails.getStatus());
        user.setPhoneNumber(userDetails.getPhoneNumber());
        user.setProfileImageUrl(userDetails.getProfileImageUrl());

        return userRepository.save(user);
      })
      .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "User with ID " + id + " was not found")
    );
  }

  public void deleteUser(String id) {
    if (!userRepository.existsById(id)) {
      throw new AppException(HttpStatus.NOT_FOUND, "User with ID " + id + " was not found");
    }
    userRepository.deleteById(id);
  }
}