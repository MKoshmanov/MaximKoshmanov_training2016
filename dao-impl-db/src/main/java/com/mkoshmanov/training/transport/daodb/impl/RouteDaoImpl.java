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

import com.mkoshmanov.training.transport.daoapi.IRouteDao;
import com.mkoshmanov.training.transport.daodb.customentity.PublicTransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route;

@Repository
public class RouteDaoImpl extends GenericDaoImpl<Route> implements IRouteDao {

	private static final String SQL_INSERT = "INSERT INTO route (number, direction) VALUES (?, ?)";

	private static final String SQL_UPDATE = "UPDATE route SET number=?, direction=? WHERE id=?";

	private static final String SQL_STOPS_ON_ROUTE = "SELECT stop.stop_name, r.number FROM stop "
			+ "RIGHT JOIN station st ON stop.id = st.stop_id "
			+ "RIGHT JOIN route r ON st.route_id = r.id WHERE r.id=?";

	@Override
	public Class<Route> getClassName() {
		return Route.class;
	}

	@Override
	public Long insert(final Route entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
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
		jdbcTemplate.update(SQL_UPDATE, new Object[] { entity.getNumber(), entity.getDirection(), entity.getId() });
	}

	@Override
	public List<PublicTransportStopAndRoute> stopsOnRoute(Long id) {
		List<PublicTransportStopAndRoute> rs = jdbcTemplate.query(SQL_STOPS_ON_ROUTE,
				new BeanPropertyRowMapper<PublicTransportStopAndRoute>(PublicTransportStopAndRoute.class));
		return rs;
	}
}
