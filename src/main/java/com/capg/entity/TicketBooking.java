package com.capg.entity;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticketbooking")
public class TicketBooking {
	@Id
	private BigInteger bookingId;
	private LocalDate bookingDate =LocalDate.now();
    private int noOfCustomers;

    
    //Ticket Booking have a relation with Costumer one to may where one ticket can have one or multiple customers
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	private List<Customer> customer  =new ArrayList<>(); 
	
	
	
	
	
	//Setters and Getters for the variables
	public BigInteger getBookingId() {
		return bookingId;
	}
	
	
	public void setBookingId(BigInteger bookingId) {
		this.bookingId = bookingId;
	}
	
	
	
	
	public List<Customer> getCustomerList() {
		return customer;
	}
    public void setCustomerList(List<Customer> customer) {
		this.customer = customer;
	}

	


	
	
	
	public int getNoOfCustomers() {
		return noOfCustomers;
	}

	public void setNoOfCustomers(int noOfCustomers) {
		this.noOfCustomers = noOfCustomers;
	}

	
	
}


