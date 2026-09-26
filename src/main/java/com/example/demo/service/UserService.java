package com.example.demo.service;

import com.example.demo.dto.request.CreateUserRequest;
import com.example.demo.dto.request.UpdateUserRequest;
import com.example.demo.dto.response.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.AppException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.specification.GenericSpecification;
import com.example.demo.util.RepositoryUtils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public UserResponse createUser(CreateUserRequest request) {
    User user = userMapper.toEntity(request);
    User savedUser = userRepository.save(user);
    return userMapper.toResponse(savedUser);
  }

  public List<UserResponse> getAllUsers(String search) {
    List<String> columnToSearch = Arrays.asList("name", "email");
    List<User> users = userRepository.findAll(GenericSpecification.searchByColumn(search, columnToSearch));
    return userMapper.toResponseList(users);
  }

  public UserResponse getUserById(String id) {
    User user = RepositoryUtils.findOrThrow(userRepository, id, "User");
    return userMapper.toResponse(user);
  }

  public UserResponse updateUser(String id, UpdateUserRequest request) {
    User user = RepositoryUtils.findOrThrow(userRepository, id, "User");
    userMapper.updateEntityFromRequest(request, user);
    User updatedUser = userRepository.save(user);
    return userMapper.toResponse(updatedUser);
  }

  public void deleteUser(String id) {
    if (!userRepository.existsById(id)) {
      throw new AppException(HttpStatus.NOT_FOUND, "User with ID " + id + " was not found");
    }
    userRepository.deleteById(id);
  }
}