package com.capg.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capg.entity.Customer;

public interface CustomerService {

public ResponseEntity<?> addCustomer(Customer customer);
	
	public Iterable<Customer> displayAllCustomer();
	
	public Customer updateCustomer(Customer customer);
	
	
	public boolean checkDataExistence(Integer customer);

	public ResponseEntity<?> findCustomerById(Integer id);

	public String deleteCustomerById(Integer id);

	public List<Customer> addAllCustomers(List<Customer> customerList);
		// TODO Auto-generated method stub
	
	
}
