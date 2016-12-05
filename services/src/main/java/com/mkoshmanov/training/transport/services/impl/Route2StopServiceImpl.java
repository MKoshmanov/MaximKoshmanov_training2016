package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daoapi.IRoute2StopDao;
import com.mkoshmanov.training.transport.daodb.customentity.TransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route2Stop;
import com.mkoshmanov.training.transport.services.IRoute2StopService;

@Service
public class Route2StopServiceImpl extends GenericServiceImpl<Route2Stop> implements IRoute2StopService {

	private static final Logger LOGGER = LoggerFactory.getLogger(Route2StopServiceImpl.class);

	@Inject
	private IRoute2StopDao routeCompositionDao;

	@Override
	public void saveAll(List<Route2Stop> all) {
		for (Route2Stop route2Stop : all) {
			save(route2Stop);
		}
	}

	@Override
	public Long save(Route2Stop route2Stop) {
		if (route2Stop.getId() == null) {
			Long id = routeCompositionDao.insert(route2Stop);
			LOGGER.info("Route2stop: id = {}, route id = {}, transport stop id = {}, sequence = {}",
					route2Stop.getId(), route2Stop.getRouteId(),
					route2Stop.getTransportStopId(), route2Stop.getSequence());
			return id;
		} else {
			routeCompositionDao.update(route2Stop);
			return route2Stop.getId();
		}
	}

	@Override
	public List<TransportStopAndRoute> routesThroughStop(Long id) {
		return routeCompositionDao.countRoutesThroughStop(id);
	}
}
