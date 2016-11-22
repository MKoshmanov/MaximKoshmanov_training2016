package com.mkoshmanov.training.transport.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.ITimetableDao;
import com.mkoshmanov.training.transport.daodb.mapper.TimetableMapper;
import com.mkoshmanov.training.transport.datamodel.Timetable;

@Repository
public class TimetableDaoImpl implements ITimetableDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Timetable getById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM timetable WHERE id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Timetable>(Timetable.class));
	}

	@Override
	public Long insert(final Timetable entity) {
		final String INSERT_SQL = "INSERT INTO timetable (public_transport_stop_id, route_id, vehicle, arrive_time) VALUES (?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
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
		jdbcTemplate.update("UPDATE transport SET public_transport_stop_id=?, route_id=?, vehicle=?, arrive_time=? WHERE id=?",
				new Object[] { entity.getPublicTransportStopId(), entity.getRouteId(), entity.getVehicle(), entity.getArriveTime() });

	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM timetable WHERE id = ?", new Object[] { id });
	}

	@Override
	public List<Timetable> getAll() {
		return this.jdbcTemplate.query("SELECT * FROM timetable", new TimetableMapper());
	}

}
