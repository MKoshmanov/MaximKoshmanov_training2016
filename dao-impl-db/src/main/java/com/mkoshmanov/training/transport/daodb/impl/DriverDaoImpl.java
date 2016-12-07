package com.mkoshmanov.training.transport.daodb.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IDriverDao;
import com.mkoshmanov.training.transport.daodb.customentity.DriversOnRoute;
import com.mkoshmanov.training.transport.datamodel.Driver;

@Repository
public class DriverDaoImpl extends GenericDaoImpl<Driver> implements IDriverDao {

	private static final String SQL_INSERT = "INSERT INTO driver (first_name, last_name, birthday, license_category) VALUES (?, ?, ?, ?)";

	private static final String SQL_UPDATE = "UPDATE driver SET first_name=?, last_name=?, birthday=?, license_category=?  WHERE id=?";

	private static final String SQL_GET_DRIVERS_ON_PARTICULAR_ROUTE = "SELECT d.first_name, d.last_name, d.birthday, "
			+ "r.number, r.name FROM driver d JOIN transport t ON t.driver_id = "
			+ "d.id JOIN route r ON r.id = t.route_id WHERE r.number = ?";

	private static final String SQL_GET_ALL_FREE_DRIVERS = "SELECT driver FROM driver EXCEPT "
			+ "SELECT driver FROM driver, transport WHERE transport.driver_id = driver.id";

	private static final String SQL_GET_ALL_BUSY_DRIVERS = "SELECT driver FROM driver, transport "
			+ "WHERE transport.driver_id = driver.id";

    @Override
	public Long insert(final Driver entity) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
            public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
                final PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
				ps.setString(1, entity.getFirstName());
				ps.setString(2, entity.getLastName());
				ps.setDate(3, (Date) entity.getBirthday());
				ps.setString(4, entity.getLicenceCategory());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
    public void update(final Driver entity) {
		jdbcTemplate.update(SQL_UPDATE, new Object[] { entity.getFirstName(), entity.getLastName(),
				entity.getBirthday(), entity.getLicenceCategory(), entity.getId() });
	}

	@Override
    public List<DriversOnRoute> getDriversOnParticularRoute(final Integer number) {
        final List<DriversOnRoute> rs = jdbcTemplate.query(SQL_GET_DRIVERS_ON_PARTICULAR_ROUTE,
				new BeanPropertyRowMapper<DriversOnRoute>(DriversOnRoute.class));
		return rs;
	}

	@Override
	public List<DriversOnRoute> getAllBusyDrivers() {
        final List<DriversOnRoute> rs = jdbcTemplate.query(SQL_GET_ALL_BUSY_DRIVERS,
				new BeanPropertyRowMapper<DriversOnRoute>(DriversOnRoute.class));
		return rs;
	}

	@Override
	public List<DriversOnRoute> getAllFreeDrivers() {
        final List<DriversOnRoute> rs = jdbcTemplate.query(SQL_GET_ALL_FREE_DRIVERS,
				new BeanPropertyRowMapper<DriversOnRoute>(DriversOnRoute.class));
		return rs;
	}
}
