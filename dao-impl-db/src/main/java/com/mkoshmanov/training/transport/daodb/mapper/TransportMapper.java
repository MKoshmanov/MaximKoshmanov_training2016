/*package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.Transport;

public class TransportMapper implements RowMapper<Transport> {

	@Override
	public Transport mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String vehicleType = rs.getString("vehicle_type");
		Long driverId = rs.getLong("driverId");
		Long routeId = rs.getLong("routeId");
		Transport entity = new Transport();
		entity.setId(id);
		entity.setVehicleType(vehicleType);
		entity.setDriverId(driverId);
		entity.setRouteId(routeId);
		return entity;
	}
}*/