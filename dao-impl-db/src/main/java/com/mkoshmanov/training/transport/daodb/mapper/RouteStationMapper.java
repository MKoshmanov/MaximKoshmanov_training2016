package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.RouteStation;
import com.mkoshmanov.training.transport.datamodel.Stop;
import com.mkoshmanov.training.transport.datamodel.Timetable;

public class RouteStationMapper implements RowMapper<RouteStation> {

	@Override
	public RouteStation mapRow(ResultSet rs, int rowNum) throws SQLException {
		Stop stop = new Stop();
		stop.setId(rs.getLong("stop_id"));
		Timetable timetable = new Timetable();
		timetable.setId(rs.getLong("timetable_id"));
		Long id = rs.getLong("id");
		RouteStation entity = new RouteStation();
		entity.setId(id);
		entity.setStop(stop);
		entity.setTransportId(rs.getLong("transport_id"));
		entity.setSequence(rs.getInt("sequence"));
		entity.setTimetable(timetable);
		return entity;
	}
}