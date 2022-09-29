package com.capg.validator;

import java.util.List;

import javax.persistence.TypedQuery;

import com.capg.entity.User;
import com.capg.exception.RecordNotFoundException;

public class QuaryClass {
	/*
	public User findByUserName(String username) throws RecordNotFoundException {
		TypedQuery<User> qry = eManager.createQuery("select u from User u where u.username like :name", User.class);
		qry.setParameter("name", username);
		List<User> user = qry.getResultList();
		if (user.size() == 0)
			throw new RecordNotFoundException("User Not Available !!");
		return user.get(0);
	}*/
}
