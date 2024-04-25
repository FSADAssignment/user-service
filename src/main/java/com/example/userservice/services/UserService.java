package com.example.userservice.services;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.userservice.dto.CredentialsDto;
import com.example.userservice.dto.SignUpDto;
import com.example.userservice.dto.UsersDto;
import com.example.userservice.exceptions.AppException;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.models.User;
import com.example.userservice.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserMapper userMapper;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }
	
	public UsersDto login(CredentialsDto credentialsDto) {
		User user = userRepository.findByLogin(credentialsDto.login())
		.orElseThrow(()->new AppException("Unknown user", HttpStatus.NOT_FOUND));
		
		if(passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), 
				user.getPassword())) {
			return userMapper.toUserDto(user);
		}
		throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
	}

	public UsersDto register(SignUpDto signUpDto) {
		Optional<User> oUser = userRepository.findByLogin(signUpDto.login());
		if(oUser.isPresent()) {
			throw new AppException("Username already exists", HttpStatus.BAD_REQUEST);
		}
		User user = userMapper.signUpToUser(signUpDto);
		user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));
		user.setFirstName(signUpDto.firstName());
		user.setLastName(signUpDto.lastName());
		user.setLogin(signUpDto.login());		
		User savedUser = userRepository.save(user);
		return userMapper.toUserDto(savedUser);
	}

}
