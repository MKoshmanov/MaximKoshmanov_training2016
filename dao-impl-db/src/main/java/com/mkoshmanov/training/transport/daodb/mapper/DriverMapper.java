package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.Driver;

public class DriverMapper implements RowMapper<Driver> {

	@Override
	public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String firstName = rs.getString("firstName");
		String lastName = rs.getString("lastName");
		Date birthday = rs.getDate("birthday");
		String licenseCategory = rs.getString("licenseCategory");
		Driver entity = new Driver();
		entity.setId(id);
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		entity.setBirthday(birthday);
		entity.setLicenseCategory(licenseCategory);
		return entity;	
	}
/*	
	public Driver mapRow2(ResultSet rs, int rowNum) throws SQLException {
		Driver driver = new Driver();
		driver.setId(rs.getLong("id"));
		driver.setFirstName(rs.getString("firstName"));
		driver.setLastName(rs.getString("lastName"));
		driver.setBirthday(rs.getDate("birthday"));
		driver.setLicenseCategory(rs.getString("licenceCategory"));

		Route route = new Route();
		route.setId(rs.getLong("id"));
		route.setNumber(rs.getInt("number"));
		route.setName(rs.getString("name"));
				
		Transport transport = new Transport();
		transport.setId(rs.getLong("id"));
		transport.setVehicleType(rs.getString("vehocleType"));
		transport.setDriverId(rs.getLong("driverId"));
		transport.setRouteId(rs.getLong("routeId"));
		
		DriversOnRoute driversOnRoute = new DriversOnRoute();
		driversOnRoute.setDriver(driver);
		driversOnRoute.setRoute(route);
		driversOnRoute.setTransport(transport);
		return driver;
	}*/
}
