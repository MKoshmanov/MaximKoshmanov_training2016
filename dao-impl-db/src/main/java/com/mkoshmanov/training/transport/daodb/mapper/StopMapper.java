package com.mkoshmanov.training.transport.daodb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.datamodel.Stop;

public class StopMapper implements RowMapper<Stop> {

	boolean stopNameEnExist;
	boolean stopNameRuExist;

	@Override
	public Stop mapRow(ResultSet rs, int rowNum) throws SQLException {
		checkColumn(rs);
		Long id = rs.getLong("id");
		Stop entity = new Stop();
		entity.setId(id);
		if (stopNameEnExist) {
			String name_en = rs.getString("name_en");
			entity.setName(name_en);
		}
		if (stopNameRuExist) {
			String name_ru = rs.getString("name_ru");
			entity.setName(name_ru);
		}
		return entity;
	}

	private void checkColumn(ResultSet rs) throws SQLException {
		int columnNumber = rs.getMetaData().getColumnCount();
		for (int i = 1; i <= columnNumber; i++) {
			String columnName = rs.getMetaData().getColumnName(i);
			if (columnName.equals("name_en")) {
				stopNameEnExist = true;
			}
			if (columnName.equals("name_ru")) {
				stopNameRuExist = true;
			}
		}
	}
}
