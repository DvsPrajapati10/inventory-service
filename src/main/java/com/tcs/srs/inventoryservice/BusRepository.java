package com.tcs.srs.inventoryservice;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BusRepository extends JpaRepository<Bus, Integer> {

	@Transactional
	@Modifying
	@Query("delete from Bus b where b.busNumber = ?1")
	int deleteBusByNumber(Integer busNumber);

	@Transactional
	@Modifying
	@Query("update Bus b set b.availableSeats = ?1, b.lastUpdateDate = ?2 where b.busNumber = ?3")
	int updateAvailableSeatsAndLastUpdateDateByBusNumber(Integer availableSeats, LocalDate lastUpdateDate,
			Integer busNumber);

}