package com.example.demo.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Status status;

  private String phoneNumber;
  private String profileImageUrl;

  @CreationTimestamp
  @Column(updatable = false)
  private Instant createdAt;

  @CreationTimestamp
  private Instant updatedAt;

  public User() {
  }
}
