package com.mkoshmanov.training.transport.daodb.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IGenericDao;

@Repository
public abstract class GenericDaoImpl<T> implements IGenericDao<T> {

	@Inject
	protected JdbcTemplate jdbcTemplate;
	
	private Class<T> entityClass;
	private String tableName;
	private RowMapper<T> rowMapper;
	private String SQL_GET_BY_ID;
	private String SQL_DELETE_BY_ID;
	private String SQL_GET_ALL;

	public abstract Class<T> getClassName();
	public abstract String getTableName();
	
	public GenericDaoImpl() {
		entityClass = getClassName();
		tableName = getTableName();
		rowMapper = new BeanPropertyRowMapper<T>(entityClass);
		SQL_GET_BY_ID = "SELECT * FROM " + tableName + " WHERE id = ?";
		SQL_DELETE_BY_ID = "DELETE FROM " + tableName + " WHERE id=?";
		SQL_GET_ALL = "SELECT * FROM " + tableName;
	}

	@Override
	public T getById(Long id) {
		return jdbcTemplate.queryForObject(SQL_GET_BY_ID, new Object[] { id },
				new BeanPropertyRowMapper<T>(entityClass));
	}

	@Override
	public void deleteById(Long id) {
		jdbcTemplate.update(SQL_DELETE_BY_ID, id);
	}

	@Override
	public List<T> getAll() {
		List<T> rs = jdbcTemplate.query(SQL_GET_ALL, rowMapper);
		return rs;
	}
}