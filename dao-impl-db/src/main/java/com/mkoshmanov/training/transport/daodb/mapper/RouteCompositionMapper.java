package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.RouteComposition;

public class RouteCompositionMapper implements RowMapper<RouteComposition> {

	@Override
	public RouteComposition mapRow(ResultSet rs, int rowNum) throws SQLException {
		Integer publicTransportStopId = rs.getInt("public_transport_stop");
		Integer routeId = rs.getInt("route_id");
		Integer sequence = rs.getInt("sequence");
		RouteComposition entity = new RouteComposition();
		entity.setPublicTransportStopId(publicTransportStopId);
		entity.setRouteId(routeId);
		entity.setSequence(sequence);
		return entity;
	}

}