package com.mkoshmanov.training.transport.daodb;

import java.util.List;

import com.mkoshmanov.training.transport.datamodel.Timetable;

public interface TimetableDao {
	
	Timetable get(Long id);

	Long insert(Timetable entity);

	void update(Timetable entity);

	void delete(Long id);

	List<Timetable> getAll();
}
