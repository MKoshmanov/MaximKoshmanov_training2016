package com.mkoshmanov.training.transport.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IRouteStationDao;
import com.mkoshmanov.training.transport.daodb.extractor.RouteStationExtractor;
import com.mkoshmanov.training.transport.daodb.mapper.RouteStationMapper;
import com.mkoshmanov.training.transport.datamodel.RouteStation;

@Repository
public class RouteStationDaoImpl extends GenericDaoImpl<RouteStation> implements IRouteStationDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(RouteStationDaoImpl.class);

	private static final String SQL_GET_BY_ID = "SELECT * FROM route_station WHERE route_station.id = ?";

	private static final String SQL_GET_ALL = "SELECT * FROM route_station";

	private static final String SQL_INSERT = "INSERT INTO route_station (stop_id, transport_id, sequence, timetable_id) VALUES (?, ?, ?, ?)";

	private static final String SQL_UPDATE = "UPDATE route_station SET stop_id=?, transport_id=?, sequence=?, timetable_id=? WHERE id=?";

	@Override
	public RouteStation getById(Long id) {
		try {
			return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new RouteStationMapper());
		} catch (DataAccessException dae) {
			LOGGER.warn("Route station with id " + id + " not exist", dae.getMessage());
			return null;
		}
	}

	@Override
	public List<RouteStation> getAll() {
		return jdbcTemplate.query(SQL_GET_ALL, new RouteStationMapper());
	}

	@Override
	public Long insert(final RouteStation entity) {
		Long id = null;
		try {
			final KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
					final PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
					ps.setObject(1, entity.getStop().getId());
					ps.setObject(2, entity.getTransportId());
					ps.setInt(3, entity.getSequence());
					ps.setObject(4, entity.getTimetable().getId());
					return ps;
				}
			}, keyHolder);
			entity.setId(keyHolder.getKey().longValue());
			id = entity.getId();
		} catch (NullPointerException npe) {
			LOGGER.warn("Route station can not be created", npe.getMessage());
			return null;
		}
		return id;
	}

	@Override
	public void update(final RouteStation entity) {
		try {
			jdbcTemplate.update(SQL_UPDATE, new Object[] { entity.getStop().getId(), entity.getTransportId(),
					entity.getSequence(), entity.getTimetable().getId(), entity.getId() });
		} catch (NullPointerException npe) {
			LOGGER.warn("Route station can not be updated", npe.getMessage());

		}
	}

	@Override
	public List<RouteStation> getRouteStationsByTransportId(Long transportId) {
	List<RouteStation> routeStations = jdbcTemplate.query("SELECT DISTINCT stop.name_en, route_station.sequence "
			+ "FROM route_station JOIN stop ON route_station.stop_id= stop.id WHERE transport_id = ?", new Object[] { transportId }, new RouteStationExtractor());
				return routeStations;
	}

}
