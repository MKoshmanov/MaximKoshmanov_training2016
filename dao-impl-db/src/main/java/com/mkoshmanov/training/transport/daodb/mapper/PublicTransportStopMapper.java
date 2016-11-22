package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.PublicTransportStop;

public class PublicTransportStopMapper implements RowMapper<PublicTransportStop> {

	@Override
	public PublicTransportStop mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String name = rs.getString("name");
		PublicTransportStop entity = new PublicTransportStop();
		entity.setId(id);
		entity.setName(name);
		return entity;
	}

}
