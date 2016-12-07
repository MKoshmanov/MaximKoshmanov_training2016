package com.mkoshmanov.training.transport.daoxml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IDriverDao;
import com.mkoshmanov.training.transport.daodb.customentity.DriversOnRoute;
import com.mkoshmanov.training.transport.datamodel.Driver;

@Repository
public class DriverDaoXmlImpl extends GenericDaoXxlImpl<Driver> implements IDriverDao {

	@Override
	public Class<Driver> getClassName() {
		return Driver.class;
	}
	
	@Override
	public String getTableName() {
		return Driver.class.getSimpleName().toLowerCase();
	}

	@Override
	public void update(Driver entity) {
		List<Driver> drivers = readCollection();
		for (Driver driver : drivers) {
			if (driver.getId().equals(entity.getId())) {
				driver.setFirstName(entity.getFirstName());
				driver.setLastName(entity.getLastName());
				driver.setBirthday(entity.getBirthday());
				driver.setLicenseCategory(entity.getLicenseCategory());
				break;
			}
			else {
				insert(entity);
			}
		}
		writeCollection(drivers);
	}

	@Override
	public List<DriversOnRoute> getDriversOnParticularRoute(Integer number) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<DriversOnRoute> getAllBusyDrivers() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Driver> getAllFreeDrivers() {
		throw new UnsupportedOperationException();
	}

}
