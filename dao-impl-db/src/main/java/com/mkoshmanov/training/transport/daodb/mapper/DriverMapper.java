package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.Driver;

public class DriverMapper implements RowMapper<Driver> {

	@Override
	public Driver mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		String firstName = rs.getString("firstName");
		String lastName = rs.getString("lastName");
		Date birthday = rs.getDate("birthday");
		String licenseCategory = rs.getString("licenseCategory");
		Driver entity = new Driver();
		entity.setId(id);
		entity.setFirstName(firstName);
		entity.setLastName(lastName);
		entity.setBirthday(birthday);
		entity.setLicenceCategory(licenseCategory);
		return entity;
	}
}
