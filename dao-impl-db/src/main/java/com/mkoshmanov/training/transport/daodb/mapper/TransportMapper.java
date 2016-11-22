package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.Transport;

public class TransportMapper implements RowMapper<Transport> {

	@Override
	public Transport mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String type = rs.getString("type");
		String registrationNumber = rs.getString("registration_number");
		Long driverId = rs.getLong("driver_id");
		Long routeId = rs.getLong("route_id");
		Transport entity = new Transport();
		entity.setId(id);
		entity.setType(type);
		entity.setRegistrationNumber(registrationNumber);
		entity.setDriverId(driverId);
		entity.setRouteId(routeId);
		return entity;
	}

}