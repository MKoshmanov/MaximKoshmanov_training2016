package com.mkoshmanov.training.transport.services;

import java.util.List;

import com.mkoshmanov.training.transport.datamodel.Driver;

public interface IDriverService extends IGenericService<Driver> {

	List<Driver> getDriversOnParticularRoute (Integer routeNumber, String vehicleType);
	
	List<Driver> getAllBusyDrivers();

	List<Driver> getAllFreeDrivers();

}
