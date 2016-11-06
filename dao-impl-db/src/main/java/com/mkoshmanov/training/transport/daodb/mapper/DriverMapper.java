package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.Driver;

public class DriverMapper implements RowMapper<Driver> {

	@Override
	public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String firstName = rs.getString("first name");
		String lastName = rs.getString("last name");
		Driver entity = new Driver();
		entity.setId(id);
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		return entity;
	}

}
