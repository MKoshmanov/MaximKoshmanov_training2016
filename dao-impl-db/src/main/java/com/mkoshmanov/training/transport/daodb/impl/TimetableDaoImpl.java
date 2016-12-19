package com.mkoshmanov.training.transport.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.ITimetableDao;
import com.mkoshmanov.training.transport.datamodel.Timetable;

@Repository
public class TimetableDaoImpl extends GenericDaoImpl<Timetable> implements ITimetableDao {

	private static final String SQL_INSERT = "INSERT INTO timetable (arrival_time) VALUES (?)";

	private static final String SQL_UPDATE = "UPDATE timetable SET arrival_time=? WHERE id=?";

	@Override
	public Long insert(final Timetable entity) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
				ps.setTime(1, entity.getArrivalTime());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(final Timetable entity) {
		jdbcTemplate.update(SQL_UPDATE, new Object[] { entity.getArrivalTime(), entity.getId() });
	}
}
