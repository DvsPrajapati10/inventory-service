package com.tcs.srs.inventoryservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class MainRestController {

	@Autowired
	BusService busService;

	@Cacheable(value = "bus", key = "#bus_number")
	@GetMapping("/get/bus/{busId}")
	public ResponseEntity<Optional<Bus>> getBusByBusId(@PathVariable String busId) {
		return new ResponseEntity<>(busService.getBusByBusId(busId), HttpStatus.OK);
	}

	@PostMapping("/createBus")
	public ResponseEntity<Bus> createBus(@RequestBody Bus bus) {
		return new ResponseEntity<>(busService.createBus(bus), HttpStatus.OK);
	}

	@DeleteMapping("/deleteBus/{busNumber}")
	public ResponseEntity<Integer> deleteBus(@PathVariable String busNumber) {
		if (busService.getBusByBusId(busNumber).isPresent()) {
			return new ResponseEntity<>(busService.deleteBus(Integer.valueOf(busNumber)), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Cacheable(value = "bus", key = "#bus_number")
	@GetMapping("/get/all/buses")
	public ResponseEntity<List<Bus>> getAllBuses() {
		return new ResponseEntity<>(busService.getAllBuses(), HttpStatus.OK);
	}

	@PutMapping("/bus")
	public ResponseEntity<Boolean> updateBusSeats(@RequestParam Integer busNumber, @RequestParam Integer noOfSeats) {
		return new ResponseEntity<>(busService.updateBusAvailableSeatsByBusId(busNumber, noOfSeats), HttpStatus.OK);
	}
}
