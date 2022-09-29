package com.capg.dao;


import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.TicketBooking;

//@Repository

public interface TicketBookingDao extends CrudRepository<TicketBooking, BigInteger>{

}




