package com.capg.controller;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capg.exception.RecordNotFoundException;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.services.CustomerService;
import com.capg.entity.Activity;
import com.capg.entity.Customer;
import com.capg.entity.TicketBooking;
import com.capg.entity.User;
import com.capg.exception.RecordAlreadyPresentException;
import com.capg.exception.RecordNotFoundException;
import com.capg.services.ActivityService;
import com.capg.services.UserService;
import com.capg.services.TicketBookingService;

@RequestMapping("/api/v2")
@RestController
public class TicketBookingController {
	@Autowired(required = true)
	TicketBookingService ticketBookingService;
	
	@Autowired(required = true)
	UserService userService;
	
	@Autowired(required = true)
	CustomerService customerService;
	
	@Autowired(required = true)
	ActivityService activityService;
	
	
	//post method for creating new Ticket 
	@PostMapping("/ticketBooking")
	@ExceptionHandler(RecordAlreadyPresentException.class) 
	public void addBooking(@RequestBody TicketBooking newBooking) {

		ticketBookingService.createTicketBooking(newBooking);
	}
	@GetMapping("/ticketBooking")
	public Iterable<TicketBooking> readAllTicketBookings() {
 
		return ticketBookingService.displayAllTicketBooking();
	}

	

	//Put mapping for updating the Ticket
	@PutMapping("/ticketBooking") 
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<TicketBooking> modifyBooking(@RequestBody TicketBooking updateBooking) {
		TicketBooking booking = ticketBookingService.updateTicketBooking(updateBooking);
		return new ResponseEntity<TicketBooking>(booking, HttpStatus.ACCEPTED);

	}

	

	//Get mapping for getting Ticket data by Id
	@GetMapping("/searchBooking/{id}")
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> searchBookingByID(@PathVariable("id") BigInteger bookingId) {

		return ticketBookingService.findBookingById(bookingId);
	}
	
	
	
	//Delete mapping for deleting Ticket by Id
	@DeleteMapping("/ticketBooking/{id}")
	public ResponseEntity<?> deleteBookingByID(@PathVariable("id") BigInteger bookingId) {

		String msg = ticketBookingService.deleteTicketBookingByID(bookingId);
		return new ResponseEntity<>(msg, HttpStatus.OK); 
	}
}
