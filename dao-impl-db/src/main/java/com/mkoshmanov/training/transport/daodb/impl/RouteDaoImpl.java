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

import com.mkoshmanov.training.transport.daodb.RouteDao;
import com.mkoshmanov.training.transport.daodb.mapper.RouteMapper;
import com.mkoshmanov.training.transport.datamodel.Route;

@Repository
public class RouteDaoImpl implements RouteDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Route get(Long id) {
		return jdbcTemplate.queryForObject("select * from route where id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Route>(Route.class));
	}

	@Override
	public Long insert(final Route entity) {
		final String INSERT_SQL = "insert into route (number, transport_id) values (?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL,
						new String[] { "id" });
				ps.setInt(1, entity.getNumber());
				ps.setInt(2, entity.getTransportId());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(Route entity) {
		jdbcTemplate.update("update route set number=?, transport_id=?, where id=?",
				new Object[] { entity.getNumber(), entity.getTransportId() });
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from route where id = ?", new Object[] { id });

	}

	@Override
	public List<Route> getAll() {
		return this.jdbcTemplate.query("select * from route", new RouteMapper());
	}

}
