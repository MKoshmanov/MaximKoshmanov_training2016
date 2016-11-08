package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daodb.RouteDao;
import com.mkoshmanov.training.transport.daodb.customentity.StopsOnRoute;
import com.mkoshmanov.training.transport.datamodel.Route;
import com.mkoshmanov.training.transport.services.RouteService;

@Service
public class RouteServiceImpl implements RouteService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);
	
	@Inject
	private RouteDao routeDao;
	
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
			LOGGER.info("New route created. id={}, number={}", route.getId(), route.getNumber());
			return id;
		} else {
			routeDao.update(route);
			return route.getId();
		}
	}
       
	
	
	
	@Override
	public Route get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Route> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<StopsOnRoute> getStopsOnParticularRoyte(Long id) {
		return routeDao.getStopsOnRoute(id);
		
	}

}
