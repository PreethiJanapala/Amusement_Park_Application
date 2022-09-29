package com.capg.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.Customer;
@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {

	public Customer findByCustomerUIN(Integer customerUin);
}
