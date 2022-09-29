package com.capg.services;

import org.springframework.http.ResponseEntity;

import com.capg.entity.User;

public interface UserService {

	public ResponseEntity<?> createUser(User newUser);

	public User updateUser(User newUser);

	public String deleteUser(Integer UserId);

	public Iterable<User> displayAllUser();

	public ResponseEntity<?> findUserById(Integer userId);
	
	public boolean checkDataExistence(User newUser);
	
	public User returnUser(Integer userId);
}
