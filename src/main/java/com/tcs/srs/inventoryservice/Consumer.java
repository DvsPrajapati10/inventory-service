package com.tcs.srs.inventoryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	@Autowired
	BusService busService;

	@Autowired
	BookingService bookingService;

	@Autowired
	Producer producer;

	Bus bus;
	Booking booking;

	@KafkaListener(topics = "inventory", groupId = "group_id")
	public void consume(String message) {
		String[] payload = message.split(":");
		String bookingNumber = payload[1];
		booking = bookingService.getBookingById(bookingNumber).get();
		bus = busService.getBusByBusId(String.valueOf(booking.getBusNumber())).get();
		if (bus.getAvailableSeats() >= booking.getNumberOfSeats()) {
			int remainingSeats = bus.getAvailableSeats() - booking.getNumberOfSeats();
			busService.updateBusAvailableSeatsByBusId(Integer.valueOf(bus.getBusNumber()), remainingSeats);
			producer.sendBookingProcessPayload("SUCCESS", bookingNumber);
		} else {
			producer.sendBookingProcessPayload("FAILURE-SEATS FULL", bookingNumber);
		}

	}
}