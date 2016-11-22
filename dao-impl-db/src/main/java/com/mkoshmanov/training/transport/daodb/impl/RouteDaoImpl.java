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

import com.mkoshmanov.training.transport.daoapi.IRouteDao;
import com.mkoshmanov.training.transport.daodb.customentity.PublicTransportStopAndRoute;
import com.mkoshmanov.training.transport.daodb.mapper.RouteMapper;
import com.mkoshmanov.training.transport.datamodel.Route;

@Repository
public class RouteDaoImpl implements IRouteDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Route getById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM route WHERE id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Route>(Route.class));
	}

	@Override
	public Long insert(final Route entity) {
		final String INSERT_SQL = "INSERT INTO route (number, direction) VALUES (?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setInt(1, entity.getNumber());
				ps.setString(2, entity.getDirection());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(Route entity) {
		jdbcTemplate.update("UPDATE route SET number=?, direction=? WHERE id=?",
				new Object[] { entity.getNumber(), entity.getDirection() });
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM route WHERE id = ?", new Object[] { id });

	}

	@Override
	public List<Route> getAll() {
		return this.jdbcTemplate.query("SELECT * FROM route", new RouteMapper());
	}

	@Override
	public List<PublicTransportStopAndRoute> stopsOnRoute(Long id) {
		List<PublicTransportStopAndRoute> rs = jdbcTemplate.query(
				"SELECT stop.stop_name, r.number FROM stop RIGHT JOIN station st ON stop.id = st.stop_id "
						+ "RIGHT JOIN route r ON st.route_id = r.id WHERE r.id=?",
				new BeanPropertyRowMapper<PublicTransportStopAndRoute>(PublicTransportStopAndRoute.class));
		return rs;
	}
}
