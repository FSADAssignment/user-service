package com.example.userservice.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.config.UserAuthProvider;
import com.example.userservice.dto.CredentialsDto;
import com.example.userservice.dto.SignUpDto;
import com.example.userservice.dto.UsersDto;
import com.example.userservice.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	
//	private final UserService userService;
//	
//	public AuthController(UserService userService) {
//		this.userService = userService;
//	}
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserAuthProvider userAuthProvider;
	
	@PostMapping("/login")
	public ResponseEntity<UsersDto> login(@RequestBody CredentialsDto credentialsDto){
		UsersDto user = userService.login(credentialsDto);
		user.setToken(userAuthProvider.createToken(user));
		return ResponseEntity.ok(user);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UsersDto> register(@RequestBody SignUpDto signUpDto){
		UsersDto user = userService.register(signUpDto);
		user.setToken(userAuthProvider.createToken(user));
		return ResponseEntity.created(URI.create("/users/"+user.getId())).body(user);
	}

}
