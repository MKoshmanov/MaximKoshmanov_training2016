package com.mkoshmanov.training.transport.daodb.impl;

import java.lang.reflect.ParameterizedType;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.mkoshmanov.training.transport.daoapi.IGenericDao;

public abstract class GenericDaoImpl<T> implements IGenericDao<T> {

    @Inject
    private JdbcTemplate jdbcTemplate;

    private final Class<T> entityClass;
    private final RowMapper<T> rowMapper;
    private final static String SQL_SELECT = "SELECT * FROM ";
    private final static String SQL_DELETE = "DELETE FROM ";
    private final static String SQL_WHERE_ID = " WHERE id = ?";

    public GenericDaoImpl() {
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.rowMapper = new BeanPropertyRowMapper<T>(entityClass);
    }

    @Override
    public T getById(final Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT + getTableName() + SQL_WHERE_ID, new Object[] { id }, new BeanPropertyRowMapper<T>(entityClass));
    }

    @Override
    public void deleteById(final Long id) {
        jdbcTemplate.update(SQL_DELETE + getTableName() + SQL_WHERE_ID, id);
    }

    @Override
    public List<T> getAll() {
        final List<T> rs = jdbcTemplate.query(SQL_SELECT + getTableName(), rowMapper);
        return rs;
    }

    private String getTableName() {
        return entityClass.getSimpleName().toLowerCase();
    }
}