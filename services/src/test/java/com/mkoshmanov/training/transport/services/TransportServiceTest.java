package com.mkoshmanov.training.transport.services;

import static org.junit.Assert.assertEquals;

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
import com.mkoshmanov.training.transport.datamodel.Route;
import com.mkoshmanov.training.transport.datamodel.Transport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-service-context.xml")
public class TransportServiceTest {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Inject
	private TransportService transportService;
	
	@Inject
	private RouteService routeService; 

	@Inject
	private DriverService driverService;

	private Transport transportOne = new Transport();
	private Transport transportTwo = new Transport();
	private Transport transportThree = new Transport();
	private Driver driverForTransportOne = new Driver();
	private Driver driverForTransportTwo = new Driver();
	private Driver driverForTransportThree = new Driver();
	private Route routeForTest = new Route();
	
	
	@Before
	public void setUp() {
		
		routeForTest.setNumber(1); 
		routeService.saveAll(Arrays.asList(routeForTest));
		
		driverForTransportOne.setFirstName("Ivan");
		driverForTransportOne.setLastName("Ivanov");
		driverForTransportTwo.setFirstName("Vasiliy");
		driverForTransportTwo.setLastName("Pupkin");
		driverForTransportThree.setFirstName("Oleg");
		driverForTransportThree.setLastName("Vasechkin");
		driverService.saveAll(Arrays.asList(driverForTransportOne, driverForTransportTwo, driverForTransportThree));

		transportOne.setVehicle("bus");
		transportOne.setRegistrationNumber("AE 0001-4");
		transportOne.setType("usual");
		transportOne.setDriverId(driverForTransportOne.getId());
		transportOne.setRouteId(routeForTest.getId());
		transportTwo.setVehicle("bus");
		transportTwo.setRegistrationNumber("AE 0002-4");
		transportTwo.setType("usual");
		transportTwo.setDriverId(driverForTransportTwo.getId());
		transportTwo.setRouteId(routeForTest.getId());
		transportThree.setVehicle("bus");
		transportThree.setRegistrationNumber("AE 0003-4");
		transportThree.setType("usual");
		transportThree.setDriverId(driverForTransportThree.getId());
		transportThree.setRouteId(routeForTest.getId());
		transportService.saveAll(Arrays.asList(transportOne, transportTwo, transportThree));

	}

	@After
	public void cleanScheama() {
		jdbcTemplate.execute("TRUNCATE transport CASCADE ");
		jdbcTemplate.execute("TRUNCATE driver CASCADE ");
		jdbcTemplate.execute("TRUNCATE route CASCADE ");
	}

	@Test
	public void shouldSaveTransportAndRetutnByRegistrationNumber() {
		Driver driverTransportForTest = new Driver();
		driverTransportForTest.setFirstName("Igor");
		driverTransportForTest.setLastName("Muhin");
		driverService.save(driverTransportForTest);
		Transport transportForTest = new Transport();
		transportForTest.setVehicle("troleybus");
		transportForTest.setRegistrationNumber("AE- 0011-4");
		transportForTest.setType("usual");
		transportForTest.setDriverId(driverTransportForTest.getId());
		transportForTest.setRouteId(routeForTest.getId());
		Long id = transportService.save(transportForTest);
		Transport transportInDataBase = transportService.get(id);
		assertEquals(transportForTest.getRegistrationNumber(), transportInDataBase.getRegistrationNumber());
	}

	@Test
	public void shouldGetAllTransport() {
		List<Transport> transports = transportService.getAll();
		assertEquals(3, transports.size());
	}

	@Test
	public void shouldDeleteTransport() {
		List<Transport> transportsBeforeDelete = transportService.getAll();
		int beforedelete = transportsBeforeDelete.size();
		transportService.delete(transportOne.getId());
		List<Transport> transportsAfterDelete = transportService.getAll();
		int afterDelete = transportsAfterDelete.size();
		assertEquals(beforedelete, afterDelete + 1);
	}
}