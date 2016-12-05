package com.mkoshmanov.training.transport.daoxml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IRoute2StopDao;
import com.mkoshmanov.training.transport.daodb.customentity.TransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route2Stop;

@Repository
public class Route2StopDaoXmlImpl extends GenericDaoXxlImpl<Route2Stop> implements IRoute2StopDao {

	@Override
	public Class<Route2Stop> getClassName() {
		return Route2Stop.class;
	}

	@Override
	public void update(Route2Stop entity) {
		List<Route2Stop> all = readCollection();
		for (Route2Stop route2stop : all) {
			if (route2stop.getId().equals(entity.getId())) {
				route2stop.setRouteId(entity.getRouteId());
				route2stop.setTransportStopId(entity.getTransportStopId());
				route2stop.setSequence(entity.getSequence());
				break;
			}
			else {
				insert(entity);
			}
		}
		writeCollection(all);
	}

	@Override
	public List<TransportStopAndRoute> countRoutesThroughStop(Long id) {
		throw new UnsupportedOperationException();
	}
}
