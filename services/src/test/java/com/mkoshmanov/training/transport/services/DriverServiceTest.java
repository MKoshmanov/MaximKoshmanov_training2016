package com.mkoshmanov.training.transport.services;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mkoshmanov.training.transport.datamodel.Driver;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-service-context.xml") 
public class DriverServiceTest {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Inject
	private IDriverService driverService;
	
	private Driver driverOne = new Driver();
	private Driver driverTwo = new Driver();
	private Driver driverThree = new Driver();
	
	@Before
	public void setUp() {
		driverOne.setFirstName("Ivan");
		driverOne.setLastName("Ivanov");
		driverTwo.setFirstName("Vasiliy");
		driverTwo.setLastName("Pupkin");
		driverThree.setFirstName("Oleg");
		driverThree.setLastName("Vasechkin");
		driverService.saveAll(Arrays.asList(driverOne, driverTwo, driverThree));
	}

	@After
	public void cleanScheama() {
		jdbcTemplate.execute("TRUNCATE driver CASCADE ");
	}

	@Test
	public void shouldSaveDriverAndRetutnById() {
		Driver driverForTest = new Driver();
		driverForTest.setFirstName("Sergey");
		driverForTest.setLastName("Nikolaev");
		Long id = driverService.save(driverForTest);
		Driver driverInDataBase = driverService.get(id);
		assertEquals(driverForTest.getId(), driverInDataBase.getId());
	}

	@Test
	public void shouldGetAllDriver() {
		List<Driver> drivers = driverService.getAll();
		assertEquals(3, drivers.size());
	}

	@Test
	public void shouldDeleteDriver() {
		List<Driver> driversBeforeDelete = driverService.getAll();
		int beforedelete = driversBeforeDelete.size();
		driverService.delete(driverOne.getId());
		List<Driver> driversAfterDelete = driverService.getAll();
		int afterDelete = driversAfterDelete.size();
		assertEquals(beforedelete, afterDelete + 1);
	}
}
