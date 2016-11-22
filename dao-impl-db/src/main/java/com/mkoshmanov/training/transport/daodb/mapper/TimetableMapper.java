package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.Timetable;

public class TimetableMapper implements RowMapper<Timetable> {

	@Override
	public Timetable mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		Integer publicTransportStopId = rs.getInt("public_transport_stop_id");
		Integer routeId = rs.getInt("route_id");
		String vehicle = rs.getString("vehicle");
		Time arriveTime = rs.getTime("arrive time");
		Timetable entity = new Timetable();
		entity.setId(id);
		entity.setPublicTransportStopId(publicTransportStopId);
		entity.setRouteId(routeId);
		entity.setVehicle(vehicle);
		entity.setArriveTime(arriveTime);
		return entity;
	}

}
