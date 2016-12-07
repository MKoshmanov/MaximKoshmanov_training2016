package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daoapi.IDriverDao;
import com.mkoshmanov.training.transport.daodb.customentity.DriversOnRoute;
import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.services.IDriverService;

@Service
public class DriverServiceImpl extends GenericServiceImpl<Driver> implements IDriverService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);

	@Inject
	private IDriverDao driverDao;

	
	@Override
	public void saveAll(List<Driver> drivers) {
		for (Driver driver : drivers) {
			save(driver);
		}
	}

	@Override
	public Long save(Driver driver) {
		if (driver.getId() == null) {
			Long id = driverDao.insert(driver);
			LOGGER.info(
					"Driver hire: id = {}, first name = {}, last name = {}, birthday = {}, driving license category = {}",
					driver.getId(), driver.getFirstName(), driver.getLastName(), driver.getBirthday(),
					driver.getLicenseCategory());
			return id;
		} else {
			driverDao.update(driver);
			return driver.getId();
		}
	}

	@Override
	public List<DriversOnRoute> getDriversOnParticularRoyte(Integer number) {
		return driverDao.getDriversOnParticularRoute(number);
	}

	@Override
	public List<DriversOnRoute> getAllBusyDrivers() {
		return driverDao.getAllBusyDrivers();
	}

	@Override
	public List<Driver> getAllFreeDrivers() {
		return driverDao.getAllFreeDrivers();
	}
}
