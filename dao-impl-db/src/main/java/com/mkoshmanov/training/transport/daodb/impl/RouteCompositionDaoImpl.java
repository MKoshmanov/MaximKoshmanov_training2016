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

import com.mkoshmanov.training.transport.daoapi.IRouteCompositionDao;
import com.mkoshmanov.training.transport.daodb.customentity.PublicTransportStopAndRoute;
import com.mkoshmanov.training.transport.daodb.mapper.RouteCompositionMapper;
import com.mkoshmanov.training.transport.datamodel.RouteComposition;

@Repository
public class RouteCompositionDaoImpl implements IRouteCompositionDao {
	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public RouteComposition getById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM route_composition WHERE id = ?", new Object[] { id },
				new BeanPropertyRowMapper<RouteComposition>(RouteComposition.class));
	}

	@Override
	public Long insert(final RouteComposition entity) {
		final String INSERT_SQL = "INSERT INTO route_composition (public_transport_stop_id, route_id, sequense) VALUES (?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
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
		jdbcTemplate.update("UPDATE station SET public_transport_stop_id=?, route_id=?, sequence=? WHERE id=?",
				new Object[] { entity.getPublicTransportStopId(), entity.getRouteId(), entity.getSequence() });
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM public_transport_stop WHERE id = ?", new Object[] { id });
	}

	@Override
	public List<RouteComposition> getAll() {
		return this.jdbcTemplate.query("select * from station", new RouteCompositionMapper());
	}

	@Override
	public List<PublicTransportStopAndRoute> countRoutesThroughStop(Long id) {
		List<PublicTransportStopAndRoute> rs = jdbcTemplate.query(
				"SELECT stop.name, r.number FROM stop JOIN station st ON stop.id = st.stop_id JOIN route r ON st.route_id = r.id WHERE stop.id = ?",
				new BeanPropertyRowMapper<PublicTransportStopAndRoute>(PublicTransportStopAndRoute.class));
		return rs;
	}

}
