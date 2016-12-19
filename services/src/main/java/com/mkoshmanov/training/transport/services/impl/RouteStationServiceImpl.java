package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daoapi.IRouteStationDao;
import com.mkoshmanov.training.transport.datamodel.RouteStation;
import com.mkoshmanov.training.transport.datamodel.Stop;
import com.mkoshmanov.training.transport.services.IRouteStationService;

@Service
public class RouteStationServiceImpl extends GenericServiceImpl<RouteStation> implements IRouteStationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RouteStationServiceImpl.class);

	@Inject
	private IRouteStationDao routeStationDao;

	@Override
	public void saveAll(List<RouteStation> routeStations) {
		for (RouteStation routeStation : routeStations) {
			save(routeStation);
		}
	}

	@Override
	public Long save(RouteStation routeStation) {
		if (routeStation.getId() == null) {
			Long id = routeStationDao.insert(routeStation);
			if (id != null) {
				return id;
			} else {
				return null;
			}
		} else {
			routeStationDao.update(routeStation);
			return routeStation.getId();
		}
	}
}
