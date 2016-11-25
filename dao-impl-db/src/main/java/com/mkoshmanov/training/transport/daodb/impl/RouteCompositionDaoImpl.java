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

import com.mkoshmanov.training.transport.daoapi.IRouteCompositionDao;
import com.mkoshmanov.training.transport.daodb.customentity.PublicTransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.RouteComposition;

@Repository
public class RouteCompositionDaoImpl extends GenericDaoImpl<RouteComposition> implements IRouteCompositionDao {

	private static final String SQL_INSERT = "INSERT INTO route_composition (public_transport_stop_id, "
			+ "route_id, sequense) VALUES (?, ?, ?)";

	private static final String SQL_UPDATE = "UPDATE route_composition SET public_transport_stop_id=?, "
			+ "route_id=?, sequence=? WHERE id=?";

	private static final String SQL_COUNT_ROUTES_THROUGH_STOP = "SELECT stop.name, r.number FROM stop "
			+ "JOIN station st ON stop.id = st.stop_id JOIN route r ON st.route_id = r.id WHERE stop.id = ?";

	@Override
	public Class<RouteComposition> getClassName() {
		return RouteComposition.class;
	}

	@Override
	public Long insert(final RouteComposition entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
				ps.setInt(1, entity.getPublicTransportStopId());
				ps.setInt(2, entity.getRouteId());
				ps.setInt(3, entity.getSequence());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(RouteComposition entity) {
		jdbcTemplate.update(SQL_UPDATE, new Object[] { entity.getPublicTransportStopId(), entity.getRouteId(),
				entity.getSequence(), entity.getId() });
	}

	@Override
	public List<PublicTransportStopAndRoute> countRoutesThroughStop(Long id) {
		List<PublicTransportStopAndRoute> rs = jdbcTemplate.query(SQL_COUNT_ROUTES_THROUGH_STOP,
				new BeanPropertyRowMapper<PublicTransportStopAndRoute>(PublicTransportStopAndRoute.class));
		return rs;
	}
}
