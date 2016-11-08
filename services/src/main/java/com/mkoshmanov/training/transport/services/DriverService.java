package com.mkoshmanov.training.transport.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mkoshmanov.training.transport.daodb.customentity.DriversOnRoute;
import com.mkoshmanov.training.transport.datamodel.Driver;

public interface DriverService {
	
	@Transactional
    void saveAll(List<Driver> drivers);

    Long save(Driver driver);

    Driver get(Long id);
    
    List<Driver> getAll();
    
    void delete(Long id);
    
    List<DriversOnRoute> driversOnParticularRoyte (Long id);
   
}
