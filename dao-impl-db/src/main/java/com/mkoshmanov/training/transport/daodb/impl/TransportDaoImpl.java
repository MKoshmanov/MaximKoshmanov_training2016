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

import com.mkoshmanov.training.transport.daoapi.ITransportDao;
import com.mkoshmanov.training.transport.daodb.mapper.TransportMapper;
import com.mkoshmanov.training.transport.datamodel.Transport;

@Repository
public class TransportDaoImpl implements ITransportDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Transport getById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM transport WHERE id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Transport>(Transport.class));
	}

	@Override
	public Long insert(final Transport entity) {
		final String INSERT_SQL = "INSERT INTO transport (type, registration_number, driver_id, route_id) values (?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getType());
				ps.setString(2, entity.getRegistrationNumber());
				ps.setLong(3, entity.getDriverId());
				ps.setLong(4, entity.getRouteId());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();

	}

	@Override
	public void update(Transport entity) {
		jdbcTemplate.update(
				"UPDATE transport SET  type=?, registrationNumber=?, driver_id=?, route_id=? WHERE id=?",
				new Object[] { entity.getType(), entity.getRegistrationNumber(), 
						entity.getDriverId(), entity.getRouteId() });

	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM transport WHERE id = ?", new Object[] { id });
	}

	@Override
	public List<Transport> getAll() {
		return this.jdbcTemplate.query("SELECT * FROM transport", new TransportMapper());

	}

}
