package com.mkoshmanov.training.transport.daodb.customentity.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.daodb.customentity.StopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route;
import com.mkoshmanov.training.transport.datamodel.Station;
import com.mkoshmanov.training.transport.datamodel.Stop;

public class StopsOnRouteMapper implements RowMapper<StopAndRoute> {

	@Override
	public StopAndRoute mapRow(ResultSet rs, int rowNum) throws SQLException {
		Route route = new Route();
		route.setId(rs.getLong("id"));
		route.setNumber(rs.getInt("number"));
		
		Station station = new Station();
		station.setId(rs.getLong("id"));
		station.setStopId(rs.getInt("stop_id"));
		station.setSequence(rs.getInt("sequence"));
		station.setId(rs.getLong("route_id"));
		
		Stop stop = new Stop();
		stop.setId(rs.getLong("id"));
		stop.setStopName(rs.getString("stop_name"));
		
		StopAndRoute stopsOnRoute = new StopAndRoute();
		stopsOnRoute.setRoute(route);
		stopsOnRoute.setStation(station);
		stopsOnRoute.setStop(stop);
		return stopsOnRoute;
	}
}
