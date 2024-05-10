package com.tcs.srs.inventoryservice;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusService {

	@Autowired
	private BusRepository busRepository;

	public Optional<Bus> getBusByBusId(String busNumber) {
		return busRepository.findById(Integer.parseInt(busNumber));
	}

	public List<Bus> getAllBuses() {
		return busRepository.findAll();
	}

	public boolean updateBusAvailableSeatsByBusId(Integer busNumber, Integer noOfRemainingSeats) {
		return busRepository.updateAvailableSeatsAndLastUpdateDateByBusNumber(noOfRemainingSeats, LocalDate.now(),
				busNumber) > 0;
	}

	public Bus createBus(Bus bus) {
		return busRepository.save(bus);
	}

	public int deleteBus(Integer busNumber) {
		return busRepository.deleteBusByNumber(busNumber);
	}
}
