package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.Transport;

public class TransportMapper implements RowMapper<Transport> {

	@Override
	public Transport mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String vehicle = rs.getString("vehicle");
		String registrationNumber = rs.getString("registration_number");
		String type = rs.getString("type");
		Long driverId = rs.getLong("driver_id");
		Transport entity = new Transport();
		entity.setId(id);
		entity.setVehicle(vehicle);
		entity.setRegistrationNumber(registrationNumber);
		entity.setType(type);
		entity.setId(driverId);
		return entity;
	}

}