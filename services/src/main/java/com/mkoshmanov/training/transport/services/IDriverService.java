package com.mkoshmanov.training.transport.services;

import java.util.List;

import com.mkoshmanov.training.transport.daodb.customentity.DriversOnRoute;
import com.mkoshmanov.training.transport.datamodel.Driver;

public interface IDriverService extends IGenericService<Driver> {

	List<DriversOnRoute> getDriversOnParticularRoyte(Integer number);

	List<DriversOnRoute> getAllBusyDrivers();

	List<DriversOnRoute> getAllFreeDrivers();

}
