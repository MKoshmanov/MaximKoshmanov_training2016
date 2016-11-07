package com.mkoshmanov.training.transport.daodb.customentity;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.datamodel.Route;
import com.mkoshmanov.training.transport.datamodel.Transport;

public class DriversOnRoute {
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private Driver driver;
	private Route route;
	private Transport transport;
		
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	public Transport getTransport() {
		return transport;
	}
	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	public List<DriversOnRoute> driversOnParticularRoute(Long id) {
		List<DriversOnRoute> rs = jdbcTemplate.query(
				"SELECT * FROM driver d RIGHT JOIN transport t ON t.driver_id = d.id RIGHT JOIN route r ON r.transport_id = t.id WHERE r.number = ?",
				new BeanPropertyRowMapper<DriversOnRoute>(DriversOnRoute.class));
		return rs;

	}
}
