package com.capg.services;

import java.math.BigInteger;

import org.springframework.http.ResponseEntity;

import com.capg.entity.TicketBooking;

public interface TicketBookingService {

	public ResponseEntity<?> createTicketBooking(TicketBooking newTicketBooking);

	public TicketBooking updateTicketBooking(TicketBooking newBooking);

	public String deleteTicketBookingByID(BigInteger bookingId);

	public Iterable<TicketBooking> displayAllTicketBooking();

	public ResponseEntity<?> findBookingById(BigInteger bookingId);
    
	
}


	





		
	