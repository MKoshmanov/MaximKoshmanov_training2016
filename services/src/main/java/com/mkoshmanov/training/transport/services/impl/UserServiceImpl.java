package com.mkoshmanov.training.transport.services.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daoapi.IUserDao;
import com.mkoshmanov.training.transport.services.IUserService;
import com.mkoshmanov.training.transport.userdetails.AppUser;

@Service
public class UserServiceImpl implements IUserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Inject
	private IUserDao userDao;

	@Override
	public AppUser loadUserByUsername(final String username) {
		LOGGER.info("In UserService");
		return userDao.getUserByUsername(username);
	}
}
