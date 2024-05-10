package com.tcs.srs.inventoryservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bus_route")
public class BusRoute {

	@Id
	@Column(name = "bus_number", nullable = false)
	private Integer id;

}