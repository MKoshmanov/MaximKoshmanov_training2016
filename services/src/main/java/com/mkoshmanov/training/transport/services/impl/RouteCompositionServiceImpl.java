package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daoapi.IRouteCompositionDao;
import com.mkoshmanov.training.transport.daodb.customentity.PublicTransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.RouteComposition;
import com.mkoshmanov.training.transport.services.IRouteCompositionService;

@Service
public class RouteCompositionServiceImpl implements IRouteCompositionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RouteCompositionServiceImpl.class);

	@Inject
	private IRouteCompositionDao routeCompositionDao;

	@Override
	public void saveAll(List<RouteComposition> routeCompositions) {
		for (RouteComposition routeComposition : routeCompositions) {
			save(routeComposition);
		}
	}

	@Override
	public Long save(RouteComposition routeComposition) {
		if (routeComposition.getId() == null) {
			Long id = routeCompositionDao.insert(routeComposition);
			LOGGER.info("Route composition. id={}, route_id={}, public_transport_stop={}, sequence={}",
					routeComposition.getId(), routeComposition.getRouteId(),
					routeComposition.getPublicTransportStopId(), routeComposition.getSequence());
			return id;
		} else {
			routeCompositionDao.update(routeComposition);
			return routeComposition.getId();
		}
	}

	@Override
	public RouteComposition get(Long id) {
		return routeCompositionDao.getById(id);
	}

	@Override
	public List<RouteComposition> getAll() {
		return routeCompositionDao.getAll();
	}

	@Override
	public void delete(Long id) {
		routeCompositionDao.delete(id);

	}

	@Override
	public List<PublicTransportStopAndRoute> routesThroughStop(Long id) {
		return routeCompositionDao.countRoutesThroughStop(id);
	}

}
