package com.mkoshmanov.training.transport.services;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.services.impl.DriverServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class DriverServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);

	@Inject
	private DriverService driverService;
	
	@Test
	public void getByIdTest() {
        Driver driver = driverService.get(6l);
        Assert.assertNotNull("driver for id=12 should not be null", driver);
        Assert.assertEquals(new Long(6), driver.getId());
    }

	@Test
	public void saveDriverTest() {
		Driver driver = new Driver();
		driver.setFirstName("Semen");
		driver.setLastName("Nikolaev");
		Long id = driverService.save(driver);
		Assert.assertNotNull(id);
		Driver driverInDataBase = driverService.get(id);
		Assert.assertEquals(driver.getId(), driverInDataBase.getId());
	}

	@Test
	public void getAllTest() {
		List<Driver> drivers = driverService.getAll();
		Assert.assertNotNull(drivers);
		System.out.println(drivers);
		LOGGER.info(drivers.toString());
	}
	
	@Test
	public void deleteDriverTest () {
		driverService.delete(13l);
		Assert.assertNull(driverService.get(13l));
	}
}
