package com.capg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.entity.User;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.exception.RecordNotFoundException;
import com.capg.services.UserService;

@RestController
@RequestMapping("/api/v1")

public class UserController {

	@Autowired
	UserService userService;

	//post method for creating new User
	@PostMapping("/user")
	@ExceptionHandler(RecordAlreadyPresentException.class)
	public ResponseEntity<?> addUser(@RequestBody User newUser) {
		boolean isDataExist = userService.checkDataExistence(newUser);
		if (isDataExist) {
			String msg = "User with ID: "+newUser.getUserId()+" has already registered!";
			return new ResponseEntity<>(msg, HttpStatus.ALREADY_REPORTED); 
		}
		else {
			userService.createUser(newUser);
			String msg = "Created User "+newUser.getUserName();
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED); 
		}
	}

	

	//Get mapping for retrieving All the User 
	@GetMapping("/user")
	public Iterable<User> readAllUser() {

		return userService.displayAllUser();
	}

	

	//Put mapping for updating the User
	@PutMapping("/user")
	@ExceptionHandler(RecordNotFoundException.class)
	public void updateUser(@RequestBody User updateUser) {
		
		userService.updateUser(updateUser);
	}
 
	
	//Get mapping for getting User data by Id
	@GetMapping("/user/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> searchUserByID(@PathVariable("id") Integer userId) {

		return userService.findUserById(userId);
	}
	
	

	//Delete mapping for deleting User by Id
	@DeleteMapping("/user/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> deleteBookingByID(@PathVariable("id") Integer userId) {
		String msg = userService.deleteUser(userId);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}