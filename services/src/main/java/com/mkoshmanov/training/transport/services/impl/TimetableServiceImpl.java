package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daoapi.ITimetableDao;
import com.mkoshmanov.training.transport.datamodel.Timetable;
import com.mkoshmanov.training.transport.services.ITimetableService;

@Service
public class TimetableServiceImpl extends GenericServiceImpl<Timetable> implements ITimetableService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimetableServiceImpl.class);

	@Inject
	private ITimetableDao timetableDao;

	@Override
	public void saveAll(List<Timetable> timetables) {
		for (Timetable timetable : timetables) {
			save(timetable);
		}
	}

	@Override
	public Long save(Timetable timetable) {
		if (timetable.getId() == null) {
			Long id = timetableDao.insert(timetable);
			LOGGER.info("Timetable created: id = {}, arrival time = {}", timetable.getId(), timetable.getArrivalTime());
			return id;
		} else {
			timetableDao.update(timetable);
			return timetable.getId();
		}
	}
}
