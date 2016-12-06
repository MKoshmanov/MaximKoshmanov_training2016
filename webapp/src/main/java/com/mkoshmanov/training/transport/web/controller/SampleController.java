package com.mkoshmanov.training.transport.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mkoshmanov.training.transport.services.components.UserDataStorage;
import com.mkoshmanov.training.transport.web.model.DriverDTO;

@RestController
@RequestMapping("/basicAuthSecured")
public class SampleController {
    @Inject
    private ApplicationContext context;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<DriverDTO>> getAll() {
        UserDataStorage userDataStorage = context.getBean(UserDataStorage.class);
        System.out.println("SampleController:" + userDataStorage);
        return new ResponseEntity<List<DriverDTO>>(new ArrayList<>(), HttpStatus.OK);
    }

}
