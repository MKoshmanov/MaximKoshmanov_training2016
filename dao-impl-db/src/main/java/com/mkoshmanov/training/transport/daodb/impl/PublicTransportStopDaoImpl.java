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

import com.mkoshmanov.training.transport.daoapi.IPublicTransportStopDao;
import com.mkoshmanov.training.transport.daodb.mapper.PublicTransportStopMapper;
import com.mkoshmanov.training.transport.datamodel.PublicTransportStop;

@Repository
public class PublicTransportStopDaoImpl implements IPublicTransportStopDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public PublicTransportStop getById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM public_transport_stop WHERE id = ?", new Object[] { id },
				new BeanPropertyRowMapper<PublicTransportStop>(PublicTransportStop.class));
	}

	@Override
	public Long insert(final PublicTransportStop entity) {

		final String INSERT_SQL = "INSERT INTO public_transport_stop (name) VALUES (?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getName());
				return ps;
			}
		}, keyHolder);

		entity.setId(keyHolder.getKey().longValue());

		return entity.getId();

	}

	@Override
	public void update(PublicTransportStop entity) {
		jdbcTemplate.update("UPDATE public_transport_stop SET name=?, WHERE id=?", new Object[] { entity.getName() });
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM public_transport_stop WHERE id = ?", new Object[] { id });
	}

	@Override
	public List<PublicTransportStop> getAll() {
		return this.jdbcTemplate.query("SELECT * FROM public_transport_stop", new PublicTransportStopMapper());
	}

}
