package com.example.userservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.userservice.dto.SignUpDto;
import com.example.userservice.dto.UsersDto;
import com.example.userservice.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UsersDto toUserDto(User user);

	@Mapping(target = "password", ignore = true)
	User signUpToUser(SignUpDto signUpDto);

}
