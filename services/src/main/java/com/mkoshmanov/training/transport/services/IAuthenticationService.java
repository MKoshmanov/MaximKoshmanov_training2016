package com.mkoshmanov.training.transport.services;

public interface IAuthenticationService {

    boolean validateUserPassword(String username, String password);
}
