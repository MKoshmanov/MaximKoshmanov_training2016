package com.mkoshmanov.training.transport.daodb.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mkoshmanov.training.transport.daoapi.IGenericDao;

public abstract class GenericDaoImpl<T> implements IGenericDao<T> {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenericDaoImpl.class);

	@Inject
	protected JdbcTemplate jdbcTemplate;

	private final Class<T> entityClass;
	private final BeanPropertyRowMapper<T> beanPropertyRowMapper;
	private final static String SQL_SELECT = "SELECT * FROM ";
	private final static String SQL_DELETE = "DELETE FROM ";
	private final static String SQL_WHERE_ID = " WHERE id = ?";

	public GenericDaoImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		this.beanPropertyRowMapper = new BeanPropertyRowMapper<T>(entityClass);
	}

	@Override
	public T getById(final Long id) {
		T t = null;
		try {
			t = jdbcTemplate.queryForObject(SQL_SELECT + getTableName() + SQL_WHERE_ID, new Object[] { id },
					new BeanPropertyRowMapper<T>(entityClass));
		} catch (DataAccessException dae) {
			LOGGER.info("Item with id = ? not exist", id, dae.getMessage());
			return null;
		}
		return t;
	}

	@Override
	public void deleteById(final Long id) {
		String tableName = getTableName();
		String finalName = null;
		if (tableName.equals("routestation")) {
			finalName = tableName.replace("routestation", "route_station");
			jdbcTemplate.update(SQL_DELETE + finalName + SQL_WHERE_ID, id);
		} else {
			jdbcTemplate.update(SQL_DELETE + getTableName() + SQL_WHERE_ID, id);
		}
	}

	@Override
	public List<T> getAll() {
		final List<T> rs = jdbcTemplate.query(SQL_SELECT + getTableName(), beanPropertyRowMapper);
		return rs;
	}

	private String getTableName() {
		return entityClass.getSimpleName().toLowerCase();
	}
}