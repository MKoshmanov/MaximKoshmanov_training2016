package com.mkoshmanov.training.transport.daoxml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IRouteStationDao;
import com.mkoshmanov.training.transport.datamodel.RouteStation;
import com.mkoshmanov.training.transport.datamodel.Stop;

@Repository
public class RouteStationDaoXmlImpl extends GenericDaoXxlImpl<RouteStation> implements IRouteStationDao {

	@Override
	public Class<RouteStation> getClassName() {
		return RouteStation.class;
	}
	
	@Override
	public String getTableName() {
		return RouteStation.class.getSimpleName().toLowerCase();
	}

	@Override
	public void update(RouteStation entity) {
		List<RouteStation> routeStations = readCollection();
		for (RouteStation routeStation : routeStations) {
			if (routeStation.getId().equals(entity.getId())) {
				routeStation.setStop(entity.getStop());
				routeStation.setTransport(entity.getTransport());
				routeStation.setSequence(entity.getSequence());
				routeStation.setTimetable(entity.getTimetable());
				break;
			}
			else {
				insert(entity);
			}
		}
		writeCollection(routeStations);
	}
}
