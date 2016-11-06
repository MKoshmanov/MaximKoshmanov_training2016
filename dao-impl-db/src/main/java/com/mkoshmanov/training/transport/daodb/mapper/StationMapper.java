package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.Station;

public class StationMapper implements RowMapper<Station> {

	@Override
	public Station mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		Integer stopId = rs.getInt("stop Id");
		Integer sequence = rs.getInt("sequence");
		Integer routeId = rs.getInt("route Id");
		Station entity = new Station();
		entity.setId(id);
		entity.setStopId(stopId);
		entity.setSequence(sequence);
		entity.setRouteId(routeId);
		return entity;
	}

}