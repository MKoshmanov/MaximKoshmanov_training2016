package com.mkoshmanov.training.transport.services;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mkoshmanov.training.transport.datamodel.Transport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-service-context.xml")
public class TransportServiceTest {

	@Inject
	private JdbcTemplate jdbcTemplate;
	@Inject
	private ITransportService transportService;
	
	private Transport transportOne = new Transport();
	private Transport transportTwo = new Transport();
		
	@Before
	public void setUp() {
		jdbcTemplate.execute("TRUNCATE transport CASCADE ");
		
		transportOne.setVehicleType("Bus");
		transportOne.setRouteNumber(1);
		transportOne.setRouteName("А - Б");
		transportTwo.setVehicleType("Trolleybus");
		transportTwo.setRouteNumber(2);
		transportTwo.setRouteName("Б - В");
		transportService.saveAll(Arrays.asList(transportOne, transportTwo));
	}
	
	@Test
	public void shouldSaveAllTransports() {
		List<Transport> transportsBeforeSave = transportService.getAll();
		int beforeSave = transportsBeforeSave.size();
		addTransports();
		List<Transport> transportsAfterSave = transportService.getAll();
		int afterSave = transportsAfterSave.size();
		assertEquals(beforeSave + 2, afterSave);
	}
	
	@Test
	public void shouldSaveTransportAndRetutnById() {
		Transport transportForTest = new Transport();
		transportForTest.setVehicleType("Bus");
		transportForTest.setRouteNumber(5);
		transportForTest.setRouteName("А - К");
		Long id = transportService.save(transportForTest);
		Transport transportInDataBase = transportService.getById(id);
		assertEquals(transportForTest.getId(), transportInDataBase.getId());
	}

	@Test
	public void shouldGetAllTransport() {
		List<Transport> transports = transportService.getAll();
		assertEquals(2, transports.size());
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
	
	@Test
	public void shouldGetAllTransportByVehicleType() {
		List<Transport> busBeforeSave = transportService.getAllRouteByVehicleType("Bus");
		int busCountBeforeSave = busBeforeSave.size();
		addTransports();
		List<Transport> busAfterSave = transportService.getAllRouteByVehicleType("Bus");
		int busCountAfterSave = busAfterSave.size();
		assertEquals(busCountBeforeSave + 1, busCountAfterSave);		
	}
	
	private void addTransports() {
		Transport transportForTest = new Transport();
		transportForTest.setVehicleType("Bus");
		transportForTest.setRouteNumber(5);
		transportForTest.setRouteName("А - К");
		Transport transportForTestTwo = new Transport();
		transportForTestTwo.setVehicleType("Trolleybus");
		transportForTestTwo.setRouteNumber(5);
		transportForTestTwo.setRouteName("А - К");
		transportService.saveAll(Arrays.asList(transportForTest, transportForTestTwo));
	}

}
