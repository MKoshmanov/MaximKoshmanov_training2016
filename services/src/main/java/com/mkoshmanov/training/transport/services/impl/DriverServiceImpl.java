package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daoapi.IDriverDao;
import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.services.IDriverService;

@Service
public class DriverServiceImpl extends GenericServiceImpl<Driver> implements IDriverService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);

	@Inject
	private IDriverDao driverDao;

	@Override
	@Cacheable("getById")
	public Driver getById(final Long id) {
		simulateSlowService();
		LOGGER.info("Trying to get driver with id: " + id);
		return driverDao.getById(id);
	}

	@Override
	public void saveAll(List<Driver> drivers) {
		for (Driver driver : drivers) {
			save(driver);
		}
	}

	@Override
	@CacheEvict(value = "getById", key = "#driver")
	public Long save(Driver driver) {
		if (driver.getId() == null) {
			Long id = driverDao.insert(driver);
			return id;
		} else {
			driverDao.update(driver);
			return driver.getId();
		}
	}

	@Override
	public List<Driver> getAllBusyDrivers() {
		return driverDao.getAllBusyDrivers();
	}

	@Override
	public List<Driver> getAllFreeDrivers() {
		return driverDao.getAllFreeDrivers();
	}

	@Override
	public List<Driver> getDriversOnParticularRoute(Integer routeNumber, String vehicleType) {
		return driverDao.getDriversOnParticularRoute(routeNumber, vehicleType);
	}

	private void simulateSlowService() {
		try {
			final long time = 5000L;
			Thread.sleep(time);
		} catch (final InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}
