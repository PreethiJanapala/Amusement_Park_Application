
package com.capg.services;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.capg.exception.RecordNotFoundException;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.dao.ActivityDao;
import com.capg.dao.CustomerDao;
import com.capg.dao.TicketBookingDao;
import com.capg.entity.Activity;
import com.capg.entity.Customer;
import com.capg.entity.TicketBooking;

@Service

public class TicketBookingServiceImp implements TicketBookingService {
	
	//Creating Dao object for TicketBooting
	@Autowired
	TicketBookingDao bookingDao;
	
	//Creating Dao object for Activity
    @Autowired(required=true)
    ActivityDao activityDao;                                                          

    //Creating Dao object for Customer
	@Autowired
	CustomerDao customerDao;
	
	
	//In this method we add new Ticket
	@Override
	public ResponseEntity<?> createTicketBooking(TicketBooking booking) {
              bookingDao.save(booking);
		return new ResponseEntity<TicketBooking>(booking, HttpStatus.OK);
	}

	
	
	//In this method we update the Ticket via Id
	@Override
	public TicketBooking updateTicketBooking(TicketBooking changedBooking) {
		Optional<TicketBooking> findBookingById = bookingDao.findById(changedBooking.getBookingId());
		if (findBookingById.isPresent()) {
			bookingDao.save(changedBooking);
		} else
			throw new RecordNotFoundException(
					"Booking with Booking Id: " + changedBooking.getBookingId() + " not exists!!");
		return changedBooking;
	}
	
	
	
	//In this method we delete any Ticket by Id
	@Override
	public String deleteTicketBookingByID(BigInteger bookingId) {

		Optional<TicketBooking> findBookingById = bookingDao.findById(bookingId);
		if (findBookingById.isPresent()) {
			bookingDao.deleteById(bookingId);
			return "Booking Deleted!!";
		} else
			throw new RecordNotFoundException("Booking not found for the entered BookingID");
	}
	
	
	
	//In this retrieve all the Ticket
	@Override
	public Iterable<TicketBooking> displayAllTicketBooking() {
		// TODO Auto-generated method stub
		return bookingDao.findAll();
	}

	
	
	//In this method we retrieve all the Ticket
	@Override
	public ResponseEntity<?> findBookingById(BigInteger bookingId) {
		Optional<TicketBooking> findById = bookingDao.findById(bookingId);
		try {
			if (findById.isPresent()) {
				TicketBooking findBooking = findById.get();
				return new ResponseEntity<TicketBooking>(findBooking, HttpStatus.OK);
			} else
				throw new RecordNotFoundException("No record found with ID " + bookingId);
		} catch (RecordNotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


}