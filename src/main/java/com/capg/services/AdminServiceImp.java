package com.capg.services;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capg.dao.AdminDao;
import com.capg.entity.Admin;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.exception.RecordNotFoundException;
import com.capg.validator.Inpvalidator;

@Service
public class AdminServiceImp implements AdminService {

	//Creating Dao object for Admin
	@Autowired
	AdminDao adminDao;
	
	//Creating object for Inputevalidator
	@Autowired
	Inpvalidator validate;
	
	
	//In this method we add new Admin
	@Override
	public ResponseEntity<?> createAdmin(Admin newAdmin) {
		// TODO Auto-generated method stub
		Optional<Admin> findAdminById = adminDao.findById(newAdmin.getAdminId());
		try {
			if (!findAdminById.isPresent()) {
				int val = new Random().nextInt(1050, 789343);
				newAdmin.setAdminId(val);
				adminDao.save(newAdmin);
				return new ResponseEntity<Admin>(newAdmin, HttpStatus.OK);
			} else
				throw new RecordAlreadyPresentException(
						"Admin with Id: " + newAdmin.getAdminId() + " already exists!!");
		} catch (RecordAlreadyPresentException e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	
	//In this method we update the Admin via Id 
	@Override
	public Admin updateAdmin(Admin updateAdmin) {
		// TODO Auto-generated method stub
		Optional<Admin> findAdminById = adminDao.findById(updateAdmin.getAdminId());
		if (findAdminById.isPresent()) {
			adminDao.save(updateAdmin);
		} else
			throw new RecordNotFoundException(
					"Admin with Id: " + updateAdmin.getAdminId() + " not exists!!");
		return updateAdmin;
	}

	
	

	//In this method we delete any Admin by Id
	@Override
	public String deleteAdmin(Integer AdminId) {
		// TODO Auto-generated method stub
		Optional<Admin> findBookingById = adminDao.findById(AdminId);
		if (findBookingById.isPresent()) {
			adminDao.deleteById(AdminId);
			return "Admin Deleted!!";
		} else
			throw new RecordNotFoundException("Admin not found for the entered UserID");
	}

	
	
	//In this retrieve all the Admin
	@Override
	public Iterable<Admin> displayAllAdmin() {
		// TODO Auto-generated method stub
		return adminDao.findAll();
	}

	
	
	//In this method we find any Admin by Id
	@Override
	public ResponseEntity<?> findAdminById(Integer adminId) {
		// TODO Auto-generated method stub
		Optional<Admin> findById = adminDao.findById(adminId);
		try {
			if (findById.isPresent()) {
				Admin findAdmin = findById.get();
				return new ResponseEntity<Admin>(findAdmin, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("No record found with ID " + adminId);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	

	//In this method we check data Existence
	@Override
	public boolean checkDataExistence(Admin newAdmin) {
		// TODO Auto-generated method stub
		Optional<Admin> admin =  adminDao.findById(newAdmin.getAdminId());
		if(admin.isPresent()) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	

	@Override
	public Admin returnAdmin(Integer adminId) {
		// TODO Auto-generated method stub
		Optional<Admin> admin =  adminDao.findById(adminId);
		if (admin.isPresent()) {
			return admin.get();
		} else
			throw new RecordNotFoundException(
					"Admin with Id: " + admin.get().getAdminId() + " doesn't exists!!");
	
	}
	}

