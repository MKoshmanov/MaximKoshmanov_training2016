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
		driver.setFirstName(rs.getString("first_name"));
		driver.setLastName(rs.getString("last_name"));

		Route route = new Route();
		route.setId(rs.getLong("id"));
		route.setNumber(rs.getInt("number"));
		route.setDirection(rs.getString("direction"));
				
		Transport transport = new Transport();
		transport.setId(rs.getLong("id"));
		transport.setType(rs.getString("type"));
		transport.setRegistrationNumber(rs.getString("registration_number"));
		transport.setDriverId(rs.getLong("driver_id"));
		transport.setRouteId(rs.getLong("route_id"));
		
		DriversOnRoute driversOnRoute = new DriversOnRoute();
		driversOnRoute.setDriver(driver);
		driversOnRoute.setRoute(route);
		driversOnRoute.setTransport(transport);
		return driversOnRoute;
	}
}
