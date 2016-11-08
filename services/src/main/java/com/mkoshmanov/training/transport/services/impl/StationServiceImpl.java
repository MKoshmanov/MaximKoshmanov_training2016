package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daodb.StationDao;
import com.mkoshmanov.training.transport.daodb.customentity.StopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Station;
import com.mkoshmanov.training.transport.services.StationService;

@Service
public class StationServiceImpl implements StationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);

	@Inject
	private StationDao stationDao;

	@Override
	public void saveAll(List<Station> stations) {
		for (Station station : stations) {
			save(station);
		}
	}

	@Override
	public Long save(Station station) {
		if (station.getId() == null) {
			Long id = stationDao.insert(station);
			LOGGER.info("Open new station. id={}, number={}", station.getId(), station.getStopId(),
					station.getSequence(), station.getRouteId());
			return id;
		} else {
			stationDao.update(station);
			return station.getId();
		}
	}

	@Override
	public Station get(Long id) {
		return stationDao.get(id);
	}

	@Override
	public List<Station> getAll() {
		return stationDao.getAll();
	}

	@Override
	public void delete(Long id) {
		stationDao.delete(id);

	}

	@Override
	public List<StopAndRoute> routesThroughStop(Long id) {
		return stationDao.countRoutesThroughStop(id);
	}

}
