package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daodb.DriverDao;
import com.mkoshmanov.training.transport.daodb.customentity.DriversOnRoute;
import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.services.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);

	@Inject
	private DriverDao driverDao;
	
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
			LOGGER.info("Driver hire. id={}, first_name={}, last_name={}", driver.getId(), driver.getFirstName(),
					driver.getLastName());
			return id;
		} else {
			driverDao.update(driver);
			return driver.getId();
		}
	}

	@Override
	public Driver get(Long id) {
		return driverDao.get(id);
	}
	
	@Override
	public void delete(Long id) {
		driverDao.delete(id);
	}

	@Override
	public List<Driver> getAll() {
		return driverDao.getAll();
	}
	
	@Override
	public List<DriversOnRoute> driversOnParticularRoyte (Long id) {
		return driverDao.driversOnParticularRoute(id);
		
	}
}
