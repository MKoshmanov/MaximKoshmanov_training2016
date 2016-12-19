package com.mkoshmanov.training.transport.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IDriverDao;
import com.mkoshmanov.training.transport.daodb.extractor.DriverExtractor;
import com.mkoshmanov.training.transport.daodb.mapper.DriverMapper;
import com.mkoshmanov.training.transport.datamodel.Driver;

@Repository
public class DriverDaoImpl extends GenericDaoImpl<Driver> implements IDriverDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverDaoImpl.class);

	private static final String SQL_GET_BY_ID = "SELECT * FROM driver WHERE driver.id = ?";

	private static final String SQL_GET_ALL = "SELECT * FROM driver";

	private static final String SQL_INSERT = "INSERT INTO driver (first_name, last_name, "
			+ "birthday, transport_id) VALUES (?, ?, ?, ?)";

	private static final String SQL_UPDATE = "UPDATE driver SET first_name=?, last_name=?, "
			+ "birthday=?, transport_id=?  WHERE id=?";
	
	private static final String SQL_GET_DRIVERS_ON_PARTICULAR_ROUTE = "SELECT * FROM driver JOIN transport "
			+ "ON driver.transport_id = transport.id WHERE transport.route_number = ? AND transport.vehicle_type = ?";
	private static final String SQL_GET_ALL_FREE_DRIVERS = "SELECT * FROM driver WHERE driver.transport_id IS NULL";

	private static final String SQL_GET_ALL_BUSY_DRIVERS = "SELECT *, driver.id AS driverId, transport.id AS transportId "
			+ "FROM driver JOIN transport ON driver.transport_id = transport.id";

	// DriverExtractor driverExtractor = new DriverExtractor();

	@Override
	public Driver getById(Long id) {
		try {
			return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id }, new DriverMapper());
		} catch (DataAccessException dae) {
			LOGGER.warn("Driver with id " + id + " not exist", dae.getMessage());
			return null;
		}
	}

	@Override
	public List<Driver> getAll() {
		return jdbcTemplate.query(SQL_GET_ALL, new DriverMapper());
	}

	@Override
	public Long insert(final Driver entity) {
		Long id = null;
		try {
			final KeyHolder keyHolder = new GeneratedKeyHolder();
			preparedStatement(entity, keyHolder);
			id = entity.getId();
		} catch (NullPointerException npe) {
			LOGGER.warn("Driver can not be created", npe.getMessage());
			return null;
		}
		return id;
	}

	private void preparedStatement (final Driver entity, final KeyHolder keyHolder) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
				ps.setString(1, entity.getFirstName());
				ps.setString(2, entity.getLastName());
				ps.setDate(3, entity.getBirthday());
				if (entity.getTransport() != null) {
					ps.setObject(4, entity.getTransport().getId());
				} else {
					ps.setNull(4, Types.INTEGER);
				}
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
	}

	@Override
	public void update(final Driver entity) {
		try {
			jdbcTemplate.update(SQL_UPDATE, new Object[] { entity.getFirstName(), entity.getLastName(),
					entity.getBirthday(), entity.getTransport().getId(), entity.getId() });
		} catch (NullPointerException npe) {
			LOGGER.warn("Driver can not be updated", npe.getMessage());

		}
	}

	@Override
	public List<Driver> getAllBusyDrivers() {
		final List<Driver> rs = jdbcTemplate.query(SQL_GET_ALL_BUSY_DRIVERS, new DriverExtractor());
		return rs;
	}

	@Override
	public List<Driver> getAllFreeDrivers() {
		final List<Driver> rs = jdbcTemplate.query(SQL_GET_ALL_FREE_DRIVERS, new DriverMapper());
		return rs;
	}

	@Override
	public List<Driver> getDriversOnParticularRoute(Integer routeNumber, String vehicleType) {
		final List<Driver> rs = jdbcTemplate.query(SQL_GET_DRIVERS_ON_PARTICULAR_ROUTE,
				new Object[] { routeNumber, vehicleType }, new DriverMapper());
		return rs;
	}
}
