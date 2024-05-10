package com.tcs.srs.inventoryservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;

	public Optional<Booking> getBookingById(String bookingNumber) {
		return bookingRepository.findById(bookingNumber);
	}

}
