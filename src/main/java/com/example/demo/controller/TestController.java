package com.example.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController 
public class TestController {

  @GetMapping("/test")
  public String getMethodName() {

    String message = "Hello World! Ini request aplikasi java pertamaku";
    int newbie = 0;

    return message + " " + newbie + " tahun";
  }
  
}
