package com.mkoshmanov.training.transport.daodb.customentity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.daodb.customentity.DriversOnRoute;
import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.datamodel.Route;
import com.mkoshmanov.training.transport.datamodel.Transport;

public class DriversOnRouteMapper implements RowMapper<DriversOnRoute> {
	@Override
	public DriversOnRoute mapRow(ResultSet rs, int rowNum) throws SQLException {
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
		return driversOnRoute;
	}
}
