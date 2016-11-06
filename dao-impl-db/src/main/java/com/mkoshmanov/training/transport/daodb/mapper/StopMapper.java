package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.Stop;

public class StopMapper implements RowMapper<Stop> {

	@Override
	public Stop mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String stopName = rs.getString("stop name");
		Stop entity = new Stop();
		entity.setId(id);
		entity.setStopName(stopName);
		return entity;
	}

}
