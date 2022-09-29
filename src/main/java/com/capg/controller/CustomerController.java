package com.capg.controller;

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

import com.capg.entity.Customer;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.exception.RecordNotFoundException;
import com.capg.services.CustomerService;
@RestController
@RequestMapping("/api/v3")
public class CustomerController {

	@Autowired
	CustomerService custService;
	
	//post method for creating new Customer
	@PostMapping("/customer")
	@ExceptionHandler(RecordAlreadyPresentException.class)
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
		    custService.addCustomer(customer);
			String msg = "Customer data registered";
			return new ResponseEntity<>(msg, HttpStatus.ACCEPTED);
	} 
	
	
	//Get mapping for retrieving All the Customer 
	@GetMapping("/customer")
	public Iterable<Customer> readAllCustomer() {
        return custService.displayAllCustomer();
	}
	
	
	//Put mapping for updating the Customer
	@PutMapping("/customer")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		
		Customer p = custService.updateCustomer(customer);
		return new ResponseEntity<Customer>(p, HttpStatus.ACCEPTED);
	}
	
	
	
	//Get mapping for getting Customer data by Id
	@GetMapping("/customer/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> searchCustomerById(@PathVariable("id") Integer id) {

		return custService.findCustomerById(id);
	}

	
	
	//Delete mapping for deleting Customer by Id
	@DeleteMapping("/customer/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> deleteCustomerById(@PathVariable("id") Integer id) {
		String msg = custService.deleteCustomerById(id);
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
