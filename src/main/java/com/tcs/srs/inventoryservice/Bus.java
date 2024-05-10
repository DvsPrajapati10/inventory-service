package com.tcs.srs.inventoryservice;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bus", schema = "public")
public class Bus implements Serializable {

	private static final long serialVersionUID = -8437263862893495363L;

	@Id
	@Column(name = "bus_number", nullable = false)
	private Integer busNumber;

	@Column(name = "available_seats")
	private Integer availableSeats;

	@Column(name = "last_update_date")
	private LocalDate lastUpdateDate;

}