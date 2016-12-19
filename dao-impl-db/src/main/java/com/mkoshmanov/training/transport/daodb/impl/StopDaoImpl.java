package com.mkoshmanov.training.transport.daodb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IStopDao;
import com.mkoshmanov.training.transport.daodb.mapper.StopMapper;
import com.mkoshmanov.training.transport.daodb.util.Utils;
import com.mkoshmanov.training.transport.datamodel.Stop;

@Repository
public class StopDaoImpl implements IStopDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(StopDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;

	private static final String SQL_GET_BY_ID = "SELECT stop.id, stop.name_en FROM stop WHERE stop.id = ?";

	private static final String SQL_GET_ALL = "SELECT stop.id, stop.name_en FROM stop";

	private static final String SQL_INSERT = "INSERT INTO stop (name_en) VALUES (?)";

	private static final String SQL_UPDATE = "UPDATE stop SET name_en=? WHERE id=?";

	private static final String SQL_DELETE_BY_ID = "DELETE FROM stop WHERE id=?";

	@Override
	public Stop getById(Long id, final Locale locale) {
		String SQL = SQL_GET_BY_ID;
		String SQL_WIHT_LOCAL = null;
		SQL_WIHT_LOCAL = Utils.checkLocale(locale, SQL);
		try {
			return jdbcTemplate.queryForObject(SQL_WIHT_LOCAL, new Object[] { id }, new StopMapper());
		} catch (DataAccessException dae) {
			LOGGER.warn("Stop with id " + id + " not exist", dae.getMessage());
			return null;
		}
	}

	@Override
	public List<Stop> getAll(Locale locale) {
		String SQL = SQL_GET_ALL;
		String SQL_WIHT_LOCAL = null;
		SQL_WIHT_LOCAL = Utils.checkLocale(locale, SQL);
		return jdbcTemplate.query(SQL_WIHT_LOCAL, new StopMapper());
	}

	@Override
	public Long insert(final Stop entity, final Locale locale) {
		final KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				String SQL = SQL_INSERT;
				String SQL_WIHT_LOCAL = null;
				SQL_WIHT_LOCAL = Utils.checkLocale(locale, SQL);
				final PreparedStatement ps = connection.prepareStatement(SQL_WIHT_LOCAL, new String[] { "id" });
				ps.setString(1, entity.getName());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().longValue());
		return entity.getId();
	}

	@Override
	public void update(final Stop entity, final Locale locale) {
		String sql = SQL_UPDATE;
		String sqlWithLocale = null;
		sqlWithLocale = Utils.checkLocale(locale, sql);
		jdbcTemplate.update(sqlWithLocale, new Object[] { entity.getName(), entity.getId() });
	}

	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update(SQL_DELETE_BY_ID, id);
	}
}
