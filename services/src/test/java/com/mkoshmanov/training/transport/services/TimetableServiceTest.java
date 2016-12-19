package com.mkoshmanov.training.transport.services;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mkoshmanov.training.transport.datamodel.Timetable;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-service-context.xml") 
public class TimetableServiceTest {
	@Inject
	private JdbcTemplate jdbcTemplate;
	@Inject
	private ITimetableService timetableService;
	
	private Timetable timetableOne = new Timetable();
	private Timetable timetableTwo = new Timetable();
		
	@Before
	public void setUp() {
		jdbcTemplate.execute("TRUNCATE timetable CASCADE ");
		timetableOne.setArrivalTime(Time.valueOf("06:00:00"));
		timetableTwo.setArrivalTime(Time.valueOf("06:30:00"));
		timetableService.saveAll(Arrays.asList(timetableOne, timetableTwo));
	}
	
	@Test
	public void shouldSaveAllTimetables() {
		List<Timetable> timetablesBeforeSave = timetableService.getAll();
		int beforeSave = timetablesBeforeSave.size();
		addTimetables();
		List<Timetable> timetablesAfterSave = timetableService.getAll();
		int afterSave = timetablesAfterSave.size();
		assertEquals(beforeSave + 2, afterSave);
	}
	
	@Test
	public void shouldSaveTimetableAndRetutnById() {
		Timetable timetableForTest = new Timetable();
		timetableForTest.setArrivalTime(Time.valueOf("08:00:00"));
		Long id = timetableService.save(timetableForTest);
		Timetable timetableInDataBase = timetableService.getById(id);
		assertEquals(timetableForTest.getId(), timetableInDataBase.getId());
	}

	@Test
	public void shouldGetAllTimetables() {
		List<Timetable> timetables = timetableService.getAll();
		assertEquals(2, timetables.size());
	}

	@Test
	public void shouldDeleteTimetable() {
		List<Timetable> timetablesBeforeDelete = timetableService.getAll();
		int beforeDelete = timetablesBeforeDelete.size();
		timetableService.delete(timetableOne.getId());
		List<Timetable> timetablesAfterDelete = timetableService.getAll();
		int afterDelete = timetablesAfterDelete.size();
		assertEquals(beforeDelete, afterDelete + 1);
	}
	
	private void addTimetables() {
		Timetable timetableForTestOne = new Timetable();
		timetableForTestOne.setArrivalTime(Time.valueOf("07:00:00"));
		Timetable timetableForTestTwo = new Timetable();
		timetableForTestTwo.setArrivalTime(Time.valueOf("07:03:00"));
		timetableService.saveAll(Arrays.asList(timetableForTestOne, timetableForTestTwo));
	}
}
