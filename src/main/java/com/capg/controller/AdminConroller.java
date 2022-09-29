package com.capg.controller;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.entity.Activity;
import com.capg.entity.Admin;
import com.capg.entity.Customer;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.exception.RecordNotFoundException;
import com.capg.services.ActivityService;
import com.capg.services.AdminService;
import com.capg.services.CustomerService;

@RestController
@RequestMapping("/api/v5")
public class AdminConroller {

	@Autowired
	AdminService adminService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ActivityService activityService;
	
	
	
	//post mapping for creating new Admin 
	@PostMapping("/admin")
	@ExceptionHandler(RecordAlreadyPresentException.class)
	public ResponseEntity<?> addAdmin(@RequestBody Admin newAdmin) {
		boolean isDataExist = adminService.checkDataExistence(newAdmin);
		if (isDataExist) {
			String msg = "Admin with ID: "+newAdmin.getAdminId()+" has already registered!";
			return new ResponseEntity<>(msg, HttpStatus.ALREADY_REPORTED); 
		}
		else {
			adminService.createAdmin(newAdmin);
			String msg = "Created Admin "+newAdmin.getAdminName();
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED); 
		}
	}
	
	
	
	//Get mapping for retrieving All the Admin
	@GetMapping("/admin")
	public Iterable<Admin> readAllAdmin() {

		return adminService.displayAllAdmin();
	}
	
	
	
	//Put mapping for updating the Activity
	@PutMapping("/admin")
	@ExceptionHandler(RecordNotFoundException.class)
	public void updateAdmin(@RequestBody Admin updateAdmin) {
		
		adminService.updateAdmin(updateAdmin);
	}
 
	
	
	//Get mapping for getting Admin data by Id
	@GetMapping("/admin/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> searchAdminByID(@PathVariable("id") Integer adminId) {

		return adminService.findAdminById(adminId);
	}

	
	
	//Delete mapping for deleting Admin by Id
	@DeleteMapping("/admin/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> deleteBookingByID(@PathVariable("id") Integer adminId) {
		String msg = adminService.deleteAdmin(adminId);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
	
	
	//Get  mapping for on customer so admin can see customer data
	@GetMapping("/customer")
	public Iterable<Customer> readAllCustomer() {

		return customerService.displayAllCustomer();
	}
	
	
	//Get  mapping for on customer so admin can see customer data
	@GetMapping("/customer/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> searchCustomerById(@PathVariable("id") Integer id) {

		return customerService.findCustomerById(id);
	}
	
	
	
	//Get  mapping for on Activity so admin can see activity data
	@GetMapping("/activity")
	public Iterable<Activity> viewAllActivity() {
		return activityService.displayAllActivitys();
	}
	
	//Post  mapping for on Activity so admin can create new Activity
	@PostMapping("/activity")
	public ResponseEntity<?> createActivity(@RequestBody Activity activity){
		
	    activityService.addActivity(activity);
		String msg = "Activity data registered";
		return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
    } 

	
	//Delete  mapping for on Activity so admin can delete any Activity
	@DeleteMapping("/activity/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> deleteActivityById(@PathVariable("id") Integer id) {
		String msg = activityService.deleteActivityById(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
