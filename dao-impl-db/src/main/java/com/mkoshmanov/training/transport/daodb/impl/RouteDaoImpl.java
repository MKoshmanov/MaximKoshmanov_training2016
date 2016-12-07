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
import com.mkoshmanov.training.transport.daodb.customentity.TransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route;

@Repository
public class RouteDaoImpl extends GenericDaoImpl<Route> implements IRouteDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	private static final String SQL_INSERT = "INSERT INTO route (number, name) VALUES (?, ?)";

	private static final String SQL_UPDATE = "UPDATE route SET number=?, name=? WHERE id=?";

	private static final String SQL_STOPS_ON_ROUTE = "SELECT ts.name, r.number FROM transport_stop ts "
			+ "JOIN route_2_stop r2s ON ts.id = r2s.transport_stop_id "
			+ "JOIN route r ON r2s.route_id = r.id WHERE r.id=?";

	@Override
	public Long insert(final Route entity) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
				ps.setInt(1, entity.getNumber());
				ps.setString(2, entity.getName());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(final Route entity) {
		jdbcTemplate.update(SQL_UPDATE, new Object[] { entity.getNumber(), entity.getName(), entity.getId() });
	}

	@Override
	public List<TransportStopAndRoute> stopsOnRoute(final Long id) {
		final List<TransportStopAndRoute> rs = jdbcTemplate.query(SQL_STOPS_ON_ROUTE,
				new BeanPropertyRowMapper<TransportStopAndRoute>(TransportStopAndRoute.class));
		return rs;
	}
}
