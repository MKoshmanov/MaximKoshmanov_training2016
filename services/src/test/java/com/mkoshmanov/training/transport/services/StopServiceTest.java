package com.mkoshmanov.training.transport.services;

import static org.junit.Assert.assertEquals;

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

import com.mkoshmanov.training.transport.datamodel.Stop;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-service-context.xml") 
public class StopServiceTest {

	@Inject
	private JdbcTemplate jdbcTemplate;
	@Inject
	private IStopService stopService;
	
	private Stop stopOne = new Stop();
	private Stop stopTwo = new Stop();
		
	@Before
	public void setUp() {
		jdbcTemplate.execute("TRUNCATE stop CASCADE ");
		stopOne.setName("A");
		stopTwo.setName("B");
		stopService.saveAll(Arrays.asList(stopOne, stopTwo), Locale.ENGLISH);
	}
	
	@Test
	public void shouldSaveAllStops() {
		List<Stop> transportsBeforeSave = stopService.getAll(Locale.ENGLISH);
		int beforeSave = transportsBeforeSave.size();
		addStops();
		List<Stop> transportsAfterSave = stopService.getAll(Locale.ENGLISH);
		int afterSave = transportsAfterSave.size();
		assertEquals(beforeSave + 2, afterSave);
	}
	
	@Test
	public void shouldSaveStopAndRetutnById() {
		Stop stopForTest = new Stop();
		stopForTest.setName("Z");
		Long id = stopService.save(stopForTest, Locale.ENGLISH);
		Stop stopInDataBase = stopService.getById(id, Locale.ENGLISH);
		assertEquals(stopForTest.getId(), stopInDataBase.getId());
	}

	@Test
	public void shouldGetAllStop() {
		List<Stop> stops = stopService.getAll(Locale.ENGLISH);
		assertEquals(2, stops.size());
	}

	@Test
	public void shouldDeleteStop() {
		List<Stop> stopsBeforeDelete = stopService.getAll(Locale.ENGLISH);
		int beforeDelete = stopsBeforeDelete.size();
		stopService.delete(stopOne.getId());
		List<Stop> stopsAfterDelete = stopService.getAll(Locale.ENGLISH);
		int afterDelete = stopsAfterDelete.size();
		assertEquals(beforeDelete, afterDelete + 1);
	}
	
	private void addStops() {
		Stop stopForTestOne = new Stop();
		stopForTestOne.setName("C");
		Stop stopForTestTwo = new Stop();
		stopForTestTwo.setName("D");
		stopService.saveAll(Arrays.asList(stopForTestOne, stopForTestTwo), Locale.ENGLISH);
	}
}