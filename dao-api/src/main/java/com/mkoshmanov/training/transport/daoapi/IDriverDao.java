package com.mkoshmanov.training.transport.daoapi;

import java.util.List;

import com.mkoshmanov.training.transport.datamodel.Driver;

public interface IDriverDao extends IGenericDao<Driver> {

	List<Driver> getDriversOnParticularRoute(Integer routeNumber, String vehicleType);

	List<Driver> getAllBusyDrivers();

	List<Driver> getAllFreeDrivers();
}