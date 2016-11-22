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

import com.mkoshmanov.training.transport.daoapi.IDriverDao;
import com.mkoshmanov.training.transport.daodb.customentity.DriversOnRoute;
import com.mkoshmanov.training.transport.datamodel.Driver;

@Repository
public class DriverDaoImpl extends GenericDao<Driver> implements IDriverDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	private String SQL_SELECT_DRIVERS_ON_PARTICULAR_ROUTE = "SELECT d.first_name, d.last_name, "
			+ "r.number, r.direction FROM driver d RIGHT JOIN transport t ON t.driver_id = "
			+ "d.id RIGHT JOIN route r ON r.id = t.route_id WHERE r.number = ?";

	private String SQL_SELECT_ALL_FREE_DRIVERS = "SELECT driver FROM driver EXCEPT "
			+ "SELECT driver FROM driver, transport WHERE transport.driver_id = driver.id";

	@Override
	public Driver getById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM driver WHERE id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Driver>(Driver.class));
	}

	@Override
	public Long insert(final Driver entity) {
		final String INSERT_SQL = "INSERT INTO driver (first_name, last_name) VALUES (?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getFirstName());
				ps.setString(2, entity.getLastName());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(Driver entity) {
		jdbcTemplate.update("UPDATE driver SET first_name=?, last_name=? WHERE id=?",
				new Object[] { entity.getFirstName(), entity.getLastName() });
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM driver WHERE id = ?", new Object[] { id });
	}

	@Override
	public List<Driver> getAll() {
		List<Driver> rs = jdbcTemplate.query("SELECT * FROM driver", new BeanPropertyRowMapper<Driver>(Driver.class));
		return rs;
	}

	@Override
	public List<DriversOnRoute> getDriversOnParticularRoute(Integer number) {
		List<DriversOnRoute> rs = jdbcTemplate.query(SQL_SELECT_DRIVERS_ON_PARTICULAR_ROUTE,
				new BeanPropertyRowMapper<DriversOnRoute>(DriversOnRoute.class));
		return rs;
	}

	@Override
	public List<DriversOnRoute> getAllBusyDrivers() {
		List<DriversOnRoute> rs = jdbcTemplate.query(
				"SELECT driver FROM driver, transport WHERE transport.driver_id = driver.id ",
				new BeanPropertyRowMapper<DriversOnRoute>(DriversOnRoute.class));
		return rs;
	}

	@Override
	public List<DriversOnRoute> getAllFreeDrivers() {
		List<DriversOnRoute> rs = jdbcTemplate.query(SQL_SELECT_ALL_FREE_DRIVERS,
				new BeanPropertyRowMapper<DriversOnRoute>(DriversOnRoute.class));
		return rs;
	}

}
