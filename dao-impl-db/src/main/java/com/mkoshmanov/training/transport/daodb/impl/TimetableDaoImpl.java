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

	private static final String SQL_INSERT = "INSERT INTO timetable (public_transport_stop_id, route_id, "
			+ "vehicle, arrive_time) VALUES (?, ?, ?, ?)";

	private static final String SQL_UPDATE = "UPDATE transport SET public_transport_stop_id=?, route_id=?, "
			+ "vehicle=?, arrive_time=? WHERE id=?";

	@Override
	public Class<Timetable> getClassName() {
		return Timetable.class;
	}

	@Override
	public Long insert(final Timetable entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
				ps.setInt(1, entity.getPublicTransportStopId());
				ps.setInt(2, entity.getRouteId());
				ps.setString(3, entity.getVehicle());
				ps.setTime(4, entity.getArriveTime());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(Timetable entity) {
		jdbcTemplate.update(SQL_UPDATE, new Object[] { entity.getPublicTransportStopId(), entity.getRouteId(),
				entity.getVehicle(), entity.getArriveTime(), entity.getId() });
	}
}
