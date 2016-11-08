package com.mkoshmanov.training.transport.daodb;

import java.util.List;

import com.mkoshmanov.training.transport.daodb.customentity.DriversOnRoute;
import com.mkoshmanov.training.transport.datamodel.Driver;

public interface DriverDao extends GenericDao<Driver> {
		
	List<DriversOnRoute> driversOnParticularRoute(Long id);
		
}