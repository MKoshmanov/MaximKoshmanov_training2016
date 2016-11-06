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

import com.mkoshmanov.training.transport.daodb.StationDao;
import com.mkoshmanov.training.transport.daodb.mapper.StationMapper;
import com.mkoshmanov.training.transport.datamodel.Station;


@Repository
public class StationDaoImpl implements StationDao {
	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Station get(Long id) {
		return jdbcTemplate.queryForObject("select * from station where id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Station>(Station.class));
	}

	@Override
	public Long insert(final Station entity) {
		final String INSERT_SQL = "insert into station (stop_id, route_id, sequense) values (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL,
						new String[] { "id" });
				ps.setInt(1, entity.getStopId());
				ps.setInt(2, entity.getRouteId());
				ps.setInt(3, entity.getSequence());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(Station entity) {
		jdbcTemplate.update("update station set stop_id=?, route_id=?, sequence=?, where id=?",
				new Object[] { entity.getStopId(), entity.getRouteId(), entity.getSequence() });
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from station where id = ?", new Object[] { id });
	}

	@Override
	public List<Station> getAll() {
		return this.jdbcTemplate.query("select * from station", new StationMapper());
	}

}
