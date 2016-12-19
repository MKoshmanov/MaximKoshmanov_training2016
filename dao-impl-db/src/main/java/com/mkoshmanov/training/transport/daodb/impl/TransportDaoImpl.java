package com.mkoshmanov.training.transport.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.ITransportDao;
import com.mkoshmanov.training.transport.daodb.extractor.StopExtractor;
import com.mkoshmanov.training.transport.daodb.util.Utils;
import com.mkoshmanov.training.transport.datamodel.Stop;
import com.mkoshmanov.training.transport.datamodel.Transport;

@Repository
public class TransportDaoImpl extends GenericDaoImpl<Transport> implements ITransportDao {

	private static final String SQL_INSERT = "INSERT INTO transport (vehicle_type, route_number, "
			+ "route_name) values (?, ?, ?)";

	private static final String SQL_UPDATE = "UPDATE transport SET vehicle_type=?, route_number=?, "
			+ "route_name=? WHERE id=?";
	
	private static final String SQL_GET_ALL_ROUTE_BY_VEHICLE_TYPE = "SELECT * FROM transport WHERE transport.vehicle_type = ?";

	private static final String SQL_GET_STOPS_ON_ROUTE_BY_ID = "SELECT DISTINCT stop.id AS stopId, stop.name_en AS name, route_station.sequence "
			+ "FROM stop JOIN route_station ON route_station.stop_id = stop.id JOIN transport "
			+ "ON route_station.transport_id = transport.id WHERE transport.id = ? ORDER BY route_station.sequence";

	StopExtractor stopExtractor = new StopExtractor();

	@Override
	public Long insert(final Transport entity) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
				ps.setString(1, entity.getVehicleType());
				ps.setInt(2, entity.getRouteNumber());
				ps.setString(3, entity.getRouteName());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(final Transport entity) {
		jdbcTemplate.update(SQL_UPDATE, new Object[] { entity.getVehicleType(), entity.getRouteNumber(),
				entity.getRouteName(), entity.getId() });
	}

	@Override
	public List<Transport> getAllTransportByVehicleType(String vehicleType) {
		final List<Transport> rs = jdbcTemplate.query(SQL_GET_ALL_ROUTE_BY_VEHICLE_TYPE, new Object[] { vehicleType },
				new BeanPropertyRowMapper<Transport>(Transport.class));
		return rs;
	}

	@Override
	public List<Stop> getStopsOnRouteById(Long id, Locale locale) {
		String sql = Utils.checkLocale(locale, SQL_GET_STOPS_ON_ROUTE_BY_ID);
		final List<Stop> rs = jdbcTemplate.query(sql, new Object[] { id }, stopExtractor);
		return rs;
	}

	@Override
	public List<Stop> getStopsOnRouteByRouteNumberAndVehicleType(Integer routeNumber, String vehicleType,
			Locale locale) {
		final List<Stop> rs = jdbcTemplate.query(SQL_GET_STOPS_ON_ROUTE_BY_ID,
				new Object[] { routeNumber, vehicleType }, stopExtractor);
		return rs;
	}
}
