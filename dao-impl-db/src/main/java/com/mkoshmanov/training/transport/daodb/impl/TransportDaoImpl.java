package com.mkoshmanov.training.transport.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.ITransportDao;
import com.mkoshmanov.training.transport.datamodel.Transport;

@Repository
public class TransportDaoImpl extends GenericDaoImpl<Transport> implements ITransportDao {

	private static final String SQL_INSERT = "INSERT INTO transport (vehicle_type, driver_id, "
			+ "route_id) values (?, ?, ?, ?)";

	private static final String SQL_UPDATE = "UPDATE transport SET  vehicle_type=?, driver_id=?, "
			+ "route_id=? WHERE id=?";

	@Override
	public Class<Transport> getClassName() {
		return Transport.class;
	}
	
	@Override
	public String getTableName() {
		return Transport.class.getSimpleName().toLowerCase();
	}

	@Override
	public Long insert(final Transport entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL_INSERT, new String[] { "id" });
				ps.setString(1, entity.getVehicleType());
				ps.setLong(2, entity.getDriverId());
				ps.setLong(3, entity.getRouteId());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(Transport entity) {
		jdbcTemplate.update(SQL_UPDATE, new Object[] { entity.getVehicleType(), 
				entity.getDriverId(), entity.getRouteId(), entity.getId() });
	}
}
