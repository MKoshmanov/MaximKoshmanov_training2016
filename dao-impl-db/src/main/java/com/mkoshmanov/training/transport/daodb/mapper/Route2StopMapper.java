package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.Route2Stop;

public class Route2StopMapper implements RowMapper<Route2Stop> {

	@Override
	public Route2Stop mapRow(ResultSet rs, int rowNum) throws SQLException {
		Integer transportStopId = rs.getInt("transportStop");
		Integer routeId = rs.getInt("routeId");
		Integer sequence = rs.getInt("sequence");
		Route2Stop entity = new Route2Stop();
		entity.setTransportStopId(transportStopId);
		entity.setRouteId(routeId);
		entity.setSequence(sequence);
		return entity;
	}
}