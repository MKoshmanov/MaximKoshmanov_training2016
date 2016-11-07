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

import com.mkoshmanov.training.transport.daodb.TransportDao;
import com.mkoshmanov.training.transport.daodb.mapper.TransportMapper;
import com.mkoshmanov.training.transport.datamodel.Transport;

@Repository
public class TransportDaoImpl implements TransportDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Transport get(Long id) {
		return jdbcTemplate.queryForObject("select * from transport where id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Transport>(Transport.class));
	}

	@Override
	public Long insert(final Transport entity) {
		final String INSERT_SQL = "insert into transport (vehicle, registration_number, type, driver_id) values (?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getVehicle());
				ps.setString(2, entity.getRegistrationNumber());
				ps.setString(3, entity.getType());
				ps.setLong(4, entity.getDriverId());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();

	}

	@Override
	public void update(Transport entity) {
		jdbcTemplate.update("update transport set vehicle=?, registrationNumber=?, type=?, driver_id=? where id=?",
				new Object[] { entity.getVehicle(), entity.getRegistrationNumber(), entity.getType(), entity.getDriverId() });

	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from transport where id = ?", new Object[] { id });
	}

	@Override
	public List<Transport> getAll() {
		return this.jdbcTemplate.query("select * from transport", new TransportMapper());

	}

}
