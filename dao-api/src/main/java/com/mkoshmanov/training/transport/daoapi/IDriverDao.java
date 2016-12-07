package com.mkoshmanov.training.transport.daoapi;

import java.util.List;

import com.mkoshmanov.training.transport.daodb.customentity.DriversOnRoute;
import com.mkoshmanov.training.transport.datamodel.Driver;

public interface IDriverDao extends IGenericDao<Driver> {
		
	List<DriversOnRoute> getDriversOnParticularRoute(Integer number);
	
	List<DriversOnRoute> getAllBusyDrivers();
	
	List<Driver> getAllFreeDrivers();
		
}