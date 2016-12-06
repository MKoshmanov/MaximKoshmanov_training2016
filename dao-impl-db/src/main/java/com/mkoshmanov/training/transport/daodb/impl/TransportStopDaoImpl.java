package com.mkoshmanov.training.transport.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.ITransportStopDao;
import com.mkoshmanov.training.transport.datamodel.TransportStop;

@Repository
public class TransportStopDaoImpl extends GenericDaoImpl<TransportStop> implements ITransportStopDao{

	private static final String SQL_INSERT = "INSERT INTO transport_stop (name) VALUES (?)";

	private static final String SQL_UPDATE = "UPDATE transport_stop SET name=? WHERE id=?";
	
	@Override
	public Class<TransportStop> getClassName() {
		return TransportStop.class;
	}
	
	@Override
	public String getTableName() {
		return TransportStop.class.getSimpleName().toLowerCase();
	}
	
	@Override
	public Long insert(final TransportStop entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
				ps.setString(1, entity.getName());
				return ps;
			}
		}, keyHolder); 	
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(TransportStop entity) {
		jdbcTemplate.update(SQL_UPDATE, new Object[] { entity.getName(), entity.getId() });
	}
}
