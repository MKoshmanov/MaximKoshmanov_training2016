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
import com.mkoshmanov.training.transport.daodb.customentity.StopsOnRoute;
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
		final String INSERT_SQL = "insert into route (number) values (?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setInt(1, entity.getNumber());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(Route entity) {
		jdbcTemplate.update("update route set number=? where id=?", new Object[] { entity.getNumber() });
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from route where id = ?", new Object[] { id });

	}

	@Override
	public List<Route> getAll() {
		return this.jdbcTemplate.query("select * from route", new RouteMapper());
	}

	@Override
	public List<StopsOnRoute> getStopsOnRoute(Long id) {
		List<StopsOnRoute> rs = jdbcTemplate.query(
				"SELECT stop.stop_name, r.number FROM stop RIGHT JOIN station st ON stop.id = st.stop_id "
						+ "RIGHT JOIN route r ON st.route_id = r.id WHERE r.id=?",
				new BeanPropertyRowMapper<StopsOnRoute>(StopsOnRoute.class));
		return rs;
	}
}
