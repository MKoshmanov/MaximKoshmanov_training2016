package com.mkoshmanov.training.transport.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IPublicTransportStopDao;
import com.mkoshmanov.training.transport.datamodel.PublicTransportStop;

@Repository
public class PublicTransportStopDaoImpl extends GenericDaoImpl<PublicTransportStop> implements IPublicTransportStopDao{

	private static final String SQL_INSERT = "INSERT INTO public_transport_stop (name) VALUES (?)";

	private static final String SQL_UPDATE = "UPDATE public_transport_stop SET name=?, WHERE id=?";
	
	@Override
	public Class<PublicTransportStop> getClassName() {
		return PublicTransportStop.class;
	}
	
	@Override
	public Long insert(final PublicTransportStop entity) {
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
	public void update(PublicTransportStop entity) {
		jdbcTemplate.update(SQL_UPDATE, new Object[] { entity.getName(), entity.getId() });
	}
}
