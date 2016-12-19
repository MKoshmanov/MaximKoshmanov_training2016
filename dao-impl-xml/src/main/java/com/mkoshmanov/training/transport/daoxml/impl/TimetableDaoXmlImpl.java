package com.mkoshmanov.training.transport.daoxml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.ITimetableDao;
import com.mkoshmanov.training.transport.datamodel.Timetable;

@Repository
public class TimetableDaoXmlImpl extends GenericDaoXxlImpl<Timetable> implements ITimetableDao {

	@Override
	public Class<Timetable> getClassName() {
		return Timetable.class;
	}
	
	@Override
	public String getTableName() {
		return Timetable.class.getSimpleName().toLowerCase();
	}

	@Override
	public void update(Timetable entity) {
		List<Timetable> timetables = readCollection();
		for (Timetable timetable : timetables) {
			if (timetable.getId().equals(entity.getId())) {
				timetable.setArrivalTime(entity.getArrivalTime());
				break;
			}
			else {
				insert(entity);
			}
		}
		writeCollection(timetables);
	}
}
