package com.mkoshmanov.training.transport.daodb;

import java.util.List;

import com.mkoshmanov.training.transport.daodb.customentity.DriversOnRoute;
import com.mkoshmanov.training.transport.datamodel.Driver;

public interface DriverDao {
	
	Driver get(Long id);

	Long insert(Driver entity);

	void update(Driver entity);

	void delete(Long id);

	List<Driver> getAll();
	
	List<DriversOnRoute> driversOnParticularRoute(Long id);
		
}