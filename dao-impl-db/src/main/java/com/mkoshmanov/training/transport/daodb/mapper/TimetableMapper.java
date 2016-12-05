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
		Integer transportStopId = rs.getInt("transportStopId");
		Integer routeId = rs.getInt("routeId");
		Time arrivalTime = rs.getTime("arrivalTime");
		Timetable entity = new Timetable();
		entity.setId(id);
		entity.setTransportStopId(transportStopId);
		entity.setRouteId(routeId);
		entity.setArrivalTime(arrivalTime);
		return entity;
	}
}
