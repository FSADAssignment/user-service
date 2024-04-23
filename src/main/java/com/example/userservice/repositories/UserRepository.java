package com.example.userservice.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByLogin(String login);

}
