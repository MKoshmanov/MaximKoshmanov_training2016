package com.mkoshmanov.training.transport.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IRoute2StopDao;
import com.mkoshmanov.training.transport.daodb.customentity.TransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route2Stop;

@Repository
public class Route2StopDaoImpl extends GenericDaoImpl<Route2Stop> implements IRoute2StopDao {

	private static final String SQL_INSERT = "INSERT INTO route_2_stop (transport_stop_id, "
			+ "route_id, sequense) VALUES (?, ?, ?)";

	private static final String SQL_UPDATE = "UPDATE route_2_stop SET transport_stop_id=?, "
			+ "route_id=?, sequence=? WHERE id=?";

	private static final String SQL_COUNT_ROUTES_THROUGH_STOP = "SELECT ts.name, r.number FROM transport_stop ts "
			+ "JOIN route_2_stop r2s ON ts.id = r2s.transport_stop_id JOIN route r ON r2s.route_id = r.id WHERE ts.id = ?";

	@Override
	public Class<Route2Stop> getClassName() {
		return Route2Stop.class;
	}
	
	@Override
	public String getTableName() {
		return Route2Stop.class.getSimpleName().toLowerCase();
	}

	@Override
	public Long insert(final Route2Stop entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
				ps.setInt(1, entity.getTransportStopId());
				ps.setInt(2, entity.getRouteId());
				ps.setInt(3, entity.getSequence());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(Route2Stop entity) {
		jdbcTemplate.update(SQL_UPDATE, new Object[] { entity.getTransportStopId(), entity.getRouteId(),
				entity.getSequence(), entity.getId() });
	}

	@Override
	public List<TransportStopAndRoute> countRoutesThroughStop(Long id) {
		List<TransportStopAndRoute> rs = jdbcTemplate.query(SQL_COUNT_ROUTES_THROUGH_STOP,
				new BeanPropertyRowMapper<TransportStopAndRoute>(TransportStopAndRoute.class));
		return rs;
	}
}
