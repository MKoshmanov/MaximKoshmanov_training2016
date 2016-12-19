package com.mkoshmanov.training.transport.daodb.impl;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IUserDao;
import com.mkoshmanov.training.transport.daodb.extractor.AppUserExtractor;
import com.mkoshmanov.training.transport.userdetails.AppUser;

@Repository
public class UserDaoImpl implements IUserDao {

	private static final String SQL_SELECT_USER_BY_USERNAME = "select *,roles.id as roleId from app_user join role as roles on app_user.role_id=roles.id where username = ?";
	@Inject
	private JdbcTemplate jdbcTemplate;

	private final AppUserExtractor userExtractor = new AppUserExtractor();

	@Override
	public AppUser getUserByUsername(final String username) {
		return jdbcTemplate.query(SQL_SELECT_USER_BY_USERNAME, new String[] { username }, userExtractor);
	}
}
