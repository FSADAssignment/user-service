package com.example.userservice.controllers;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.dto.CredentialsDto;
import com.example.userservice.dto.SignUpDto;
import com.example.userservice.dto.UserDto;
import com.example.userservice.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
	private final UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto){
		UserDto user = userService.login(credentialsDto);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto){
		UserDto user = userService.register(signUpDto);
		return ResponseEntity.created(URI.create("/users/"+user.getId())).body(user);
	}

}
