package com.capg.validator;

import org.springframework.stereotype.Component;

@Component
public class InpvalidatorImp implements Inpvalidator {

	
	//Validation for Name
	@Override
	public boolean nameValidator(String name) {
		// TODO Auto-generated method stub

		return name.length() >= 3;
	}

	//Validation for Contact No
	@Override
	public boolean contactValidator(String contact) {
		// TODO Auto-generated method stub
		return contact.matches("[0-9]{10}");
	}

	//Validation for Email Address
	@Override
	public boolean emailValidator(String email) {
		// TODO Auto-generated method stub
		return email.matches("^(.+)@(.+)$");	
	}

	//Validation for Password
	@Override
	public boolean passwordValidator(String password) {
		// TODO Auto-generated method stub
		return password.length() >= 3;
	}

	////Validation for userName
	@Override
	public boolean usernameValidator(String username) {
		// TODO Auto-generated method stub
		return username.matches("[A-Za-z]{3,20}$");
	}

}
