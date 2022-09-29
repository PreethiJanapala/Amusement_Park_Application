package com.capg.services;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capg.dao.UserDao;
import com.capg.entity.User;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.exception.RecordNotFoundException;
import com.capg.validator.Inpvalidator;

@Service
public class UserServiceImp implements UserService {

	//Creating Dao object for User
	@Autowired
	UserDao userDao;
	
	//Creating object for Inputevalidator
	@Autowired
	Inpvalidator validate;
	
	
	//In this method we add new User
	@Override
	public ResponseEntity<?> createUser(User newUser) {
		// TODO Auto-generated method stub
		Optional<User> findUserById = userDao.findById(newUser.getUserId());
		try {
			if (!findUserById.isPresent() ) {
				int val = new Random().nextInt(1050, 789343);
				newUser.setUserId(val);
				userDao.save(newUser);
				return new ResponseEntity<User>(newUser, HttpStatus.OK);
			} else
				throw new RecordAlreadyPresentException(
						"User with Id: " + newUser.getUserId() + " already exists!! or wrong writen");
		} catch (RecordAlreadyPresentException e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	//In this method we update the User via Id 
	@Override
	public User updateUser(User updateUser) {
		// TODO Auto-generated method stub
		Optional<User> findUserById = userDao.findById(updateUser.getUserId());
		if (findUserById.isPresent()) {
			userDao.save(updateUser);
		} else
			throw new RecordNotFoundException(
					"User with Id: " + updateUser.getUserId() + " not exists!!");
		return updateUser;
	}

	
	//In this method we delete any User by Id
	@Override
	public String deleteUser(Integer UserId) {
		// TODO Auto-generated method stub
		Optional<User> findBookingById = userDao.findById(UserId);
		if (findBookingById.isPresent()) {
			userDao.deleteById(UserId);
			return "User Deleted!!";
		} else
			throw new RecordNotFoundException("User not found for the entered UserID");
	}

	
	//In this retrieve all the User
	@Override
	public Iterable<User> displayAllUser() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	
	//In this method we find any User by Id
	@Override
	public ResponseEntity<?> findUserById(Integer userId) {
		// TODO Auto-generated method stub
		Optional<User> findById = userDao.findById(userId);
		try {
			if (findById.isPresent()) {
				User findUser = findById.get();
				return new ResponseEntity<User>(findUser, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("No record found with ID " + userId);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	//In this method we check data Existence
	@Override
	public boolean checkDataExistence(User newUser) {
		Optional<User> user =  userDao.findById(newUser.getUserId());
		if(user.isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	//In this method we check data Existence by userId
	@Override
	public User returnUser(Integer userId) {
		Optional<User> user =  userDao.findById(userId);
		if (user.isPresent()) {
			return user.get();
		} else
			throw new RecordNotFoundException(
					"User with Id: " + user.get().getUserId() + " doesn't exists!!");
	
	}
	
}
