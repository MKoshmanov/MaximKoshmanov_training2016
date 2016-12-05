package com.mkoshmanov.training.transport.services.impl;

import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.services.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Override
    public boolean validateUserPassword(String username, String password) {
        // TODO DAO query
        return username.equals("validuser") && password.equals("validpassword");
    }

}
