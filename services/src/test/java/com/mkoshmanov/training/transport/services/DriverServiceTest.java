package com.mkoshmanov.training.transport.services;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.services.impl.DriverServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-service-context.xml")
public class DriverServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;
	@Inject
	private DriverService driverService;
	private Driver driver = new Driver();
	private Driver driver1 = new Driver();
	private Driver driver2 = new Driver();
	private Driver savedDriver = new Driver();

	@Before
	public void setUp() {
		driver.setFirstName("Ivan");
		driver.setLastName("Ivanov");

		driver1.setFirstName("Vasiliy");
		driver1.setLastName("Pupkin");

		driver2.setFirstName("Oleg");
		driver2.setLastName("Vasechkin");

		savedDriver.setFirstName("Ivan");
		savedDriver.setLastName("Nosov");

		driverService.saveAll(Arrays.asList(driver, driver1, driver2));

	}

	@After
	public void cleanScheama() {
		jdbcTemplate.execute("TRUNCATE driver CASCADE ");

	}

	@Test

	public void shouldReturnDriverById() {
		Driver driver = driverService.get(15l);
		assertEquals((Long) 15L, driver.getId());
	}

	@Test
	public void saveDriverTest() {
		Long id = driverService.save(savedDriver);
		
		Driver driverInDataBase = driverService.get(id);
		assertEquals(savedDriver.getId(), driverInDataBase.getId());
	}

	@Test
	public void getAllTest() {
		List<Driver> drivers = driverService.getAll();
		
	
		assertEquals(3, drivers.size());
	}

	@Test

	public void deleteDriverTest() {
		List<Driver> driversBeforeDelete = driverService.getAll();
		int beforedelete = driversBeforeDelete.size();
		driverService.delete(driver.getId());
		List<Driver> driversAfterDelete = driverService.getAll();
		int afterDelete = driversAfterDelete.size();
		assertEquals(afterDelete+1, beforedelete);

	}
}
