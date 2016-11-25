package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daoapi.IRouteDao;
import com.mkoshmanov.training.transport.daodb.customentity.PublicTransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route;
import com.mkoshmanov.training.transport.services.IRouteService;

@Service
public class RouteServiceImpl extends GenericServiceImpl<Route> implements IRouteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RouteServiceImpl.class);

	@Inject
	private IRouteDao routeDao;

	@Override
	public void saveAll(List<Route> routes) {
		for (Route route : routes) {
			save(route);
		}
	}

	@Override
	public Long save(Route route) {
		if (route.getId() == null) {
			Long id = routeDao.insert(route);
			LOGGER.info("New route created: id = {}, number = {}, direction = {}", route.getId(), route.getNumber(), route.getDirection());
			return id;
		} else {
			routeDao.update(route);
			return route.getId();
		}
	}

	@Override
	public List<PublicTransportStopAndRoute> stopsOnRoute(Long id) {
		return routeDao.stopsOnRoute(id);
	}
}
