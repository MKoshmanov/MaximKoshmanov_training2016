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

import com.mkoshmanov.training.transport.daodb.StopDao;
import com.mkoshmanov.training.transport.daodb.customentity.DriversOnRoute;
import com.mkoshmanov.training.transport.daodb.customentity.StopsOnRoute;
import com.mkoshmanov.training.transport.daodb.mapper.StopMapper;
import com.mkoshmanov.training.transport.datamodel.Stop;

@Repository
public class StopDaoImpl implements StopDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Stop get(Long id) {
		return jdbcTemplate.queryForObject("select * from stop where id = ?", new Object[] { id },
				new BeanPropertyRowMapper<Stop>(Stop.class));
	}

	@Override
	public Long insert(final Stop entity) {

		final String INSERT_SQL = "insert into stop (stop_name) values(?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getStopName());
				return ps;
			}
		}, keyHolder);

		entity.setId(keyHolder.getKey().longValue());

		return entity.getId();

	}

	@Override
	public void update(Stop entity) {
		jdbcTemplate.update("update stop set stop_name=?, where id=?", new Object[] { entity.getStopName() });
	}

	@Override
	public void delete(Long id) {
		jdbcTemplate.update("delete from stop where id = ?", new Object[] { id });
	}

	@Override
	public List<Stop> getAll() {
		return this.jdbcTemplate.query("select * from stop", new StopMapper());
	}

	

}
