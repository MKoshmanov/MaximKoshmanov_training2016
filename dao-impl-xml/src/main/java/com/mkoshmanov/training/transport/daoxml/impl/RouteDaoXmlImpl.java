package com.mkoshmanov.training.transport.daoxml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IRouteDao;
import com.mkoshmanov.training.transport.daodb.customentity.TransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route;

@Repository
public class RouteDaoXmlImpl extends GenericDaoXxlImpl<Route> implements IRouteDao {

	@Override
	public Class<Route> getClassName() {
		return Route.class;
	}
	
	@Override
	public String getTableName() {
		return Route.class.getSimpleName().toLowerCase();
	}

	@Override
	public void update(Route entity) {
		List<Route> routes = readCollection();
		for (Route route : routes) {
			if (route.getId().equals(entity.getId())) {
				route.setNumber(entity.getNumber());
				route.setName(entity.getName());
				break;
			}
			else {
				insert(entity);
			}
		}
		writeCollection(routes);
	}

	@Override
	public List<TransportStopAndRoute> stopsOnRoute(Long id) {
		throw new UnsupportedOperationException();
	}
}
