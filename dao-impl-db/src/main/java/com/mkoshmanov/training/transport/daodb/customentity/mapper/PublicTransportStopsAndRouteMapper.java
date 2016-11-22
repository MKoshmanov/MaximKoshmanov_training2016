package com.mkoshmanov.training.transport.daodb.customentity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.daodb.customentity.PublicTransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route;
import com.mkoshmanov.training.transport.datamodel.RouteComposition;
import com.mkoshmanov.training.transport.datamodel.PublicTransportStop;

public class PublicTransportStopsAndRouteMapper implements RowMapper<PublicTransportStopAndRoute> {

	@Override
	public PublicTransportStopAndRoute mapRow(ResultSet rs, int rowNum) throws SQLException {
		Route route = new Route();
		route.setId(rs.getLong("id"));
		route.setNumber(rs.getInt("number"));

		RouteComposition routeComposition = new RouteComposition();
		routeComposition.setPublicTransportStopId(rs.getInt("public_transport_stop_id"));
		routeComposition.setRouteId(rs.getInt("route_id"));
		routeComposition.setSequence(rs.getInt("sequence"));

		PublicTransportStop publicTransportStop = new PublicTransportStop();
		publicTransportStop.setId(rs.getLong("id"));
		publicTransportStop.setName(rs.getString("name"));

		PublicTransportStopAndRoute publicTransportStopAndRoute = new PublicTransportStopAndRoute();
		publicTransportStopAndRoute.setRoute(route);
		publicTransportStopAndRoute.setRouteComposition(routeComposition);
		publicTransportStopAndRoute.setPublicTransportStop(publicTransportStop);
		return publicTransportStopAndRoute;
	}
}
