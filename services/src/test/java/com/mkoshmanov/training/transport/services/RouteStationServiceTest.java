package com.mkoshmanov.training.transport.services;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mkoshmanov.training.transport.datamodel.RouteStation;
import com.mkoshmanov.training.transport.datamodel.Stop;
import com.mkoshmanov.training.transport.datamodel.Timetable;
import com.mkoshmanov.training.transport.datamodel.Transport;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-service-context.xml")
public class RouteStationServiceTest {

	@Inject
	private JdbcTemplate jdbcTemplate;
	@Inject
	private IRouteStationService routeStationService;
	@Inject
	private IStopService stopService;
	@Inject
	private ITransportService transportService;
	@Inject
	private ITimetableService timetableService;

	private RouteStation routeStationOne = new RouteStation();
	private RouteStation routeStationTwo = new RouteStation();
	private Stop stopOne = new Stop();
	private Stop stopTwo = new Stop();
	private Transport transportOne = new Transport();
	private Transport transportTwo = new Transport();
	private Timetable timetableOne = new Timetable();
	private Timetable timetableTwo = new Timetable();

	@Before
	public void setUp() {
		jdbcTemplate.execute("TRUNCATE route_station CASCADE");
		jdbcTemplate.execute("TRUNCATE stop CASCADE");
		jdbcTemplate.execute("TRUNCATE transport CASCADE");
		jdbcTemplate.execute("TRUNCATE timetable CASCADE");

		transportOne.setVehicleType("Bus");
		transportOne.setRouteNumber(1);
		transportOne.setRouteName("А - B");
		transportTwo.setVehicleType("Trolleybus");
		transportTwo.setRouteNumber(2);
		transportTwo.setRouteName("B - A");
		transportService.saveAll(Arrays.asList(transportOne, transportTwo));
		stopOne.setName("A");
		stopTwo.setName("B");
		stopService.saveAll(Arrays.asList(stopOne, stopTwo), Locale.ENGLISH);
		timetableOne.setArrivalTime(Time.valueOf("06:00:00"));
		timetableTwo.setArrivalTime(Time.valueOf("07:00:00"));
		timetableService.saveAll(Arrays.asList(timetableOne, timetableTwo));
		routeStationOne.setStop(stopOne);
		routeStationOne.setTransport(transportOne);
		routeStationOne.setTimetable(timetableOne);
		routeStationOne.setSequence(1);
		routeStationTwo.setStop(stopTwo);
		routeStationTwo.setTransport(transportOne);
		routeStationTwo.setTimetable(timetableTwo);
		routeStationTwo.setSequence(2);
		routeStationService.saveAll(Arrays.asList(routeStationOne, routeStationTwo));
	}

	@Test
	public void shouldSaveAllRouteStations() {
		List<RouteStation> routeStationBeforeSave = routeStationService.getAll();
		int beforeSave = routeStationBeforeSave.size();
		RouteStation routeStationForTest = new RouteStation();
		routeStationForTest.setStop(stopTwo);
		routeStationForTest.setTransport(transportTwo);
		routeStationForTest.setTimetable(timetableOne);
		routeStationForTest.setSequence(1);
		RouteStation routeStationForTestTwo = new RouteStation();
		routeStationForTestTwo.setStop(stopOne);
		routeStationForTestTwo.setTransport(transportTwo);
		routeStationForTestTwo.setTimetable(timetableTwo);
		routeStationForTestTwo.setSequence(2);
		routeStationService.saveAll(Arrays.asList(routeStationForTest, routeStationForTestTwo));
		List<RouteStation> routeStationAfterSave = routeStationService.getAll();
		int afterSave = routeStationAfterSave.size();
		assertEquals(beforeSave + 2, afterSave);
	}

	@Test
	public void shouldSaveRouteStationAndRetutnById() {
		RouteStation routeStationForTest = new RouteStation();
		routeStationForTest.setStop(stopTwo);
		routeStationForTest.setTransport(transportTwo);
		routeStationForTest.setTimetable(timetableOne);
		routeStationForTest.setSequence(1);
		Long id = routeStationService.save(routeStationForTest);
		RouteStation routeStationDataBase = routeStationService.getById(id);
		assertEquals(routeStationForTest.getId(), routeStationDataBase.getId());
	}

	@Test
	public void shouldGetAllRouteStation() {
		List<RouteStation> drivers = routeStationService.getAll();
		assertEquals(2, drivers.size());
	}

	@Test
	public void shouldDeleteRouteStation() {
		List<RouteStation> routeStationsBeforeDelete = routeStationService.getAll();
		int beforeDelete = routeStationsBeforeDelete.size();
		routeStationService.delete(routeStationOne.getId());
		List<RouteStation> routeStationsAfterDelete = routeStationService.getAll();
		int afterDelete = routeStationsAfterDelete.size();
		assertEquals(beforeDelete, afterDelete + 1);
	}
}
