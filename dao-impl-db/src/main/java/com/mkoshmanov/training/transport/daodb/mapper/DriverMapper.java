package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.datamodel.Transport;

public class DriverMapper implements RowMapper<Driver> {

	@Override
	public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
		Transport transport = new Transport();
		transport.setId(rs.getLong("transport_id"));
		Driver driver = new Driver();
		driver.setId(rs.getLong("id"));
		driver.setFirstName(rs.getString("first_name"));
		driver.setLastName(rs.getString("last_name"));
		driver.setBirthday(rs.getDate("birthday"));
		driver.setTransport(transport);
		return driver;	
	}	
}
