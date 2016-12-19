package com.mkoshmanov.training.transport.services;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.datamodel.Transport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-service-context.xml")
public class DriverServiceTest {

	@Inject
	private JdbcTemplate jdbcTemplate;
	@Inject
	private IDriverService driverService;
	@Inject
	private ITransportService transportService;

	private Driver driverOne = new Driver();
	private Driver driverTwo = new Driver();
	private Driver driverThree = new Driver();
	private Transport transportOne = new Transport();
	private Transport transportTwo = new Transport();

	@Before
	public void setUp() {
		jdbcTemplate.execute("TRUNCATE driver CASCADE");
		jdbcTemplate.execute("TRUNCATE transport CASCADE");

		transportOne.setVehicleType("Bus");
		transportOne.setRouteNumber(1);
		transportOne.setRouteName("А - Б");
		transportTwo.setVehicleType("Trolleybus");
		transportTwo.setRouteNumber(2);
		transportTwo.setRouteName("Б - В");
		transportService.saveAll(Arrays.asList(transportOne, transportTwo));
		driverOne.setFirstName("Ivan");
		driverOne.setLastName("Ivanov");
		driverOne.setBirthday(Date.valueOf("1963-12-04"));
		driverOne.setTransport(transportOne);
		driverTwo.setFirstName("Vasiliy");
		driverTwo.setLastName("Pupkin");
		driverTwo.setBirthday(Date.valueOf("1981-02-14"));
		driverTwo.setTransport(transportTwo);
		driverThree.setFirstName("Oleg");
		driverThree.setLastName("Vasechkin");
		driverThree.setBirthday(Date.valueOf("1976-01-01"));
		driverThree.setTransport(transportOne);
		driverService.saveAll(Arrays.asList(driverOne, driverTwo, driverThree));
	}

	@Test
	public void shouldSaveAllDrivers() {
		List<Driver> driversBeforeSave = driverService.getAll();
		int beforeSave = driversBeforeSave.size();
		Driver driverForTest = new Driver();
		driverForTest.setFirstName("Sergey");
		driverForTest.setLastName("Nikolaev");
		driverForTest.setBirthday(Date.valueOf("1987-01-01"));
		driverForTest.setTransport(transportOne);
		Driver driverForTest1 = new Driver();
		driverForTest1.setFirstName("Vasiliy");
		driverForTest1.setLastName("Nosov");
		driverForTest1.setBirthday(Date.valueOf("1984-01-01"));
		driverForTest1.setTransport(transportTwo);
		driverService.saveAll(Arrays.asList(driverForTest, driverForTest1));
		List<Driver> driversAfterSave = driverService.getAll();
		int afterSave = driversAfterSave.size();
		assertEquals(beforeSave + 2, afterSave);
	}

	@Test
	public void shouldSaveDriverAndRetutnById() {
		Driver driverForTest = new Driver();
		driverForTest.setFirstName("Sergey");
		driverForTest.setLastName("Nikolaev");
		driverForTest.setBirthday(Date.valueOf("1987-01-01"));
		driverForTest.setTransport(null);
		Long id = driverService.save(driverForTest);
		Driver driverInDataBase = driverService.getById(id);
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

	@Test
	public void shouldGetDriversOnParticularRoute() {
		List<Driver> allDrivers = driverService.getAll();
		int allDriversCount = allDrivers.size();
		List<Driver> driversOnFirstRoute = driverService.getDriversOnParticularRoute(1, "Bus");
		int driversCountOnFirstRoute = driversOnFirstRoute.size();
		assertEquals(allDriversCount, driversCountOnFirstRoute + 1);
	}

	@Test
	public void shouldGetAllFreeDrivers() {
		List<Driver> freeDriversInDataBase = driverService.getAllFreeDrivers();
		int countFreeDrivers = freeDriversInDataBase.size();
		Driver driverForTestOne = new Driver();
		driverForTestOne.setFirstName("Sergey");
		driverForTestOne.setLastName("Nikolaev");
		driverForTestOne.setBirthday(Date.valueOf("1987-01-01"));
		driverForTestOne.setTransport(null);
		Driver driverForTestTwo = new Driver();
		driverForTestTwo.setFirstName("Ivan");
		driverForTestTwo.setLastName("Nikolaev");
		driverForTestTwo.setBirthday(Date.valueOf("1987-01-01"));
		driverForTestTwo.setTransport(transportOne);
		driverService.saveAll(Arrays.asList(driverForTestOne, driverForTestTwo));
		List<Driver> freeDriversAfterAdd = driverService.getAllFreeDrivers();
		int countFreeDriverAfterAdd = freeDriversAfterAdd.size();
		assertEquals(countFreeDrivers + 1, countFreeDriverAfterAdd);
	}

	@Test
	public void shouldGetAllBusyDrivers() {
		List<Driver> freeDriversInDataBase = driverService.getAllBusyDrivers();
		int countFreeDrivers = freeDriversInDataBase.size();
		Driver driverForTestOne = new Driver();
		driverForTestOne.setFirstName("Sergey");
		driverForTestOne.setLastName("Nikolaev");
		driverForTestOne.setBirthday(Date.valueOf("1987-01-01"));
		driverForTestOne.setTransport(null);
		Driver driverForTestTwo = new Driver();
		driverForTestTwo.setFirstName("Ivan");
		driverForTestTwo.setLastName("Nikolaev");
		driverForTestTwo.setBirthday(Date.valueOf("1987-01-01"));
		driverForTestTwo.setTransport(null);
		driverService.saveAll(Arrays.asList(driverForTestOne, driverForTestTwo));
		List<Driver> freeDriversAfterAdd = driverService.getAllBusyDrivers();
		int countFreeDriverAfterAdd = freeDriversAfterAdd.size();
		assertEquals(countFreeDrivers, countFreeDriverAfterAdd);
	}
}
