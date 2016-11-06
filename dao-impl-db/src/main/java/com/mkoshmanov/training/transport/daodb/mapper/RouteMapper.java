package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.Route;

public class RouteMapper implements RowMapper<Route> {

	@Override
	public Route mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		Integer number = rs.getInt("number");
		Integer transportId = rs.getInt("transport id");
		Route entity = new Route();
		entity.setId(id);
		entity.setNumber(number);
		entity.setTransportId(transportId);
		return entity;
	}

}