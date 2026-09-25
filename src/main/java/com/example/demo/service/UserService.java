package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.specification.GenericSpecification;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

  public Optional<User> getUserById(String id) {
    return  userRepository.findById(id);
  }

  public User updateUser(String id, User userDetails) {
    return userRepository.findById(id).map(user -> {
      user.setRole(userDetails.getRole());
      user.setStatus(userDetails.getStatus());
      user.setPhoneNumber(userDetails.getPhoneNumber());
      user.setProfileImageUrl(userDetails.getProfileImageUrl());

      return userRepository.save(user);
    }).orElseThrow(() -> new RuntimeException("User not found"));
  }

  public void deleteUser(String id) {
    userRepository.deleteById(id);
  }
}
