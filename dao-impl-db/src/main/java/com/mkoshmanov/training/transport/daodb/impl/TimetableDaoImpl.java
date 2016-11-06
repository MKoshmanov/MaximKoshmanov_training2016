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

import com.mkoshmanov.training.transport.daodb.TimetableDao;
import com.mkoshmanov.training.transport.daodb.mapper.TimetableMapper;
import com.mkoshmanov.training.transport.datamodel.Timetable;

@Repository
public class TimetableDaoImpl implements TimetableDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Timetable get(Long id) {
		return jdbcTemplate.queryForObject("select * from timetable where id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Timetable>(Timetable.class));

	}

	@Override
	public Long insert(final Timetable entity) {
		final String INSERT_SQL = "insert into timetable (station_id, route_id, arrive_time) values (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setInt(1, entity.getStationId());
				ps.setInt(2, entity.getRouteId());
				ps.setTime(3, entity.getArriveTime());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(Timetable entity) {
		jdbcTemplate.update("update transport set station_id=?, route_id=?, arrive_time=?, where id=?",
				new Object[] { entity.getStationId(), entity.getRouteId(), entity.getArriveTime() });

	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from timetable where id = ?", new Object[] { id });
	}

	@Override
	public List<Timetable> getAll() {
		return this.jdbcTemplate.query("select * from timetable", new TimetableMapper());
	}

}
