package com.capg.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capg.dao.CustomerDao;
import com.capg.entity.Customer;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.exception.RecordNotFoundException;

@Service
public class CustomerServiceImp implements CustomerService {

	//Creating Dao object for Customer
	@Autowired
	CustomerDao customerDao;
	
	
	
	//In this method we add new Customer
	@Override
	public ResponseEntity<?> addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Optional<Customer> findUserById = customerDao.findById(customer.getPnrNumber());
		try {
			if (!findUserById.isPresent()) {
				int pnrNumber = new Random().nextInt(50000, 100000);
				int uinNumber = new Random().nextInt(1000, 80000);

				customer.setPnrNumber(pnrNumber);
				customer.setCustomerUIN(uinNumber);

				customerDao.save(customer);
				return new ResponseEntity<>(customer, HttpStatus.OK);
			} else
				throw new RecordAlreadyPresentException(
						"Customer with UIN: " + customer.getCustomerUIN() + " already exists!!");
		} catch (RecordAlreadyPresentException e) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
	
	//In this retrieve all the Customer
    @Override
	public Iterable<Customer> displayAllCustomer() {
		// TODO Auto-generated method stub
             return customerDao.findAll();
	 }
	
	
	
	
    //In this method we update the Customer via Id 
	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Optional<Customer> findUserById = customerDao.findById(customer.getCustomerUIN());
		if (findUserById.isPresent()) {
			customerDao.save(customer);
		} else
			throw new RecordNotFoundException("Customer with UIN: " + customer.getCustomerUIN() + " not exists!!");
		return customer;
	}

	
	
	//In this method we check data Existence
	@Override
	public boolean checkDataExistence(Integer customer) {
		// TODO Auto-generated method stub
		Optional<Customer> cust = customerDao.findById(customer);
		if (cust.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	
	
	//In this method we find any Customer by Id
	@Override
	public ResponseEntity<?> findCustomerById(Integer customerId) {
		// TODO Auto-generated method stub
		Optional<Customer> findById = customerDao.findById(customerId);
		try {
			if (findById.isPresent()) {
				Customer findCustomer = findById.get();
				return new ResponseEntity<Customer>(findCustomer, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("No record found with ID " + customerId);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	
	
	
	//In this method we delete any Customer by Id
	@Override
	public String deleteCustomerById(Integer customerId) {
		// TODO Auto-generated method stub
		Optional<Customer> findCustomerById = customerDao.findById(customerId);
		if (findCustomerById.isPresent()) {
			customerDao.deleteById(customerId);
			return "Customer Deleted!!";
		} else
			throw new RecordNotFoundException("Customer not found for the entered UserID");
	}

	
	//In this method we retrieve all the Customer
	@Override
	public List<Customer> addAllCustomers(List<Customer> customerList) {
		Iterator<Customer> itr = customerList.iterator();
		List<Customer> resultList = new ArrayList<>();
		while (itr.hasNext()) {
			Customer pass = itr.next();

			int pnrNumber = new Random().nextInt(50000, 100000);
			int uinNumber = new Random().nextInt(1000, 80000);

			pass.setPnrNumber(pnrNumber);
			pass.setCustomerUIN(uinNumber);
			pass.setCustomerName(pass.getCustomerName());
			pass.setCustomerAge(pass.getCustomerAge());

			customerDao.save(pass);
			resultList.add(pass);
		}
		return resultList;
	}                           
	}

