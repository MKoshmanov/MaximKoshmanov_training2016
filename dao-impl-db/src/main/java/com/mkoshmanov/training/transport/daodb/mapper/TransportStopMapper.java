package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.TransportStop;

public class TransportStopMapper implements RowMapper<TransportStop> {

	@Override
	public TransportStop mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String name = rs.getString("name");
		TransportStop entity = new TransportStop();
		entity.setId(id);
		entity.setName(name);
		return entity;
	}

}
