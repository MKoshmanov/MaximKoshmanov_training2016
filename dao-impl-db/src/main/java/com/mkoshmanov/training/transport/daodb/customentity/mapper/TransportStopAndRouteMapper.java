package com.mkoshmanov.training.transport.daodb.customentity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.daodb.customentity.TransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route;
import com.mkoshmanov.training.transport.datamodel.Route2Stop;
import com.mkoshmanov.training.transport.datamodel.TransportStop;

public class TransportStopAndRouteMapper implements RowMapper<TransportStopAndRoute> {

	@Override
	public TransportStopAndRoute mapRow(ResultSet rs, int rowNum) throws SQLException {
		Route route = new Route();
		route.setId(rs.getLong("id"));
		route.setNumber(rs.getInt("number"));
		route.setName(rs.getString("name"));

		Route2Stop route2Stop = new Route2Stop();
		route2Stop.setTransportStopId(rs.getInt("transportStopId"));
		route2Stop.setRouteId(rs.getInt("routeId"));
		route2Stop.setSequence(rs.getInt("sequence"));

		TransportStop transportStop = new TransportStop();
		transportStop.setId(rs.getLong("id"));
		transportStop.setName(rs.getString("name"));
		
		TransportStopAndRoute transportStopAndRoute = new TransportStopAndRoute();
		transportStopAndRoute.setRoute(route);
		transportStopAndRoute.setRoute2Stop(route2Stop);
		transportStopAndRoute.setTransportStop(transportStop);
		return transportStopAndRoute;
	}
}
