package com.example.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String login;
	private String token;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
