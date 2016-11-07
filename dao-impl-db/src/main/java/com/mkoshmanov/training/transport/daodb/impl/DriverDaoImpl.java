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

import com.mkoshmanov.training.transport.daodb.DriverDao;
import com.mkoshmanov.training.transport.daodb.customentity.DriversOnRoute;
import com.mkoshmanov.training.transport.datamodel.Driver;

@Repository
public class DriverDaoImpl implements DriverDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Driver get(Long id) {
		return jdbcTemplate.queryForObject("select * from driver where id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Driver>(Driver.class));
	}

	@Override
	public Long insert(final Driver entity) {
		final String INSERT_SQL = "insert into driver (first_name, last_name) values (?, ?)";
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
		jdbcTemplate.update("update driver set first_name=?, last_name=?, where id=?",
				new Object[] { entity.getFirstName(), entity.getLastName() });
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from driver where id = ?", new Object[] { id });
	}

	@Override
	public List<Driver> getAll() {
		List<Driver> rs = jdbcTemplate.query("select * from driver", new BeanPropertyRowMapper<Driver>(Driver.class));
		return rs;
	}

}
