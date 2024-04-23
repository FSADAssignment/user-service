package com.example.userservice.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
public class MessageController {

	@GetMapping("/messages")
	public ResponseEntity<List<String>> messages(){
		return ResponseEntity.ok(Arrays.asList("first","second"));
	}
	
	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}
}
