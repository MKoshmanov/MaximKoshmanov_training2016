package com.mkoshmanov.training.transport.daoapi;

import com.mkoshmanov.training.transport.userdetails.AppUser;

public interface IUserDao {

	AppUser getUserByUsername(String username);
}
