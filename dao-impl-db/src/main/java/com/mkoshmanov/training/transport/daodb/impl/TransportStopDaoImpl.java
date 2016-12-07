package com.mkoshmanov.training.transport.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.ITransportStopDao;
import com.mkoshmanov.training.transport.datamodel.TransportStop;

@Repository
public class TransportStopDaoImpl extends GenericDaoImpl<TransportStop> implements ITransportStopDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_INSERT = "INSERT INTO transport_stop (name) VALUES (?)";

	private static final String SQL_UPDATE = "UPDATE transport_stop SET name=? WHERE id=?";

	@Override
	public Long insert(final TransportStop entity) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				final PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
				ps.setString(1, entity.getName());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(final TransportStop entity) {
		jdbcTemplate.update(SQL_UPDATE, new Object[] { entity.getName(), entity.getId() });
	}
}
