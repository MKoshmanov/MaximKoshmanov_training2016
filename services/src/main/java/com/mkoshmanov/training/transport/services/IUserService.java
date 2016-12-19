package com.mkoshmanov.training.transport.services;

import com.mkoshmanov.training.transport.userdetails.AppUser;

public interface IUserService {
	
    AppUser loadUserByUsername(String username);
}