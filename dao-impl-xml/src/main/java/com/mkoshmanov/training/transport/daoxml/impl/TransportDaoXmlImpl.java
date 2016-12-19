package com.mkoshmanov.training.transport.daoxml.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.ITransportDao;
import com.mkoshmanov.training.transport.datamodel.Stop;
import com.mkoshmanov.training.transport.datamodel.Transport;

@Repository
public class TransportDaoXmlImpl extends GenericDaoXxlImpl<Transport> implements ITransportDao {

	@Override
	public Class<Transport> getClassName() {
		return Transport.class;
	}
	
	@Override
	public String getTableName() {
		return Transport.class.getSimpleName().toLowerCase();
	}

	@Override
	public void update(Transport entity) {
		List<Transport> transports = readCollection();
		for (Transport transport : transports) {
			if (transport.getId().equals(entity.getId())) {
				transport.setVehicleType(entity.getVehicleType());
				transport.setRouteNumber(entity.getRouteNumber());
				transport.setRouteName(entity.getRouteName());
				break;
			}
			else {
				insert(entity);
			}
		}
		writeCollection(transports);
	}

	@Override
	public List<Transport> getAllTransportByVehicleType(String vehicleType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stop> getStopsOnRouteById(Long id, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Stop> getStopsOnRouteByRouteNumberAndVehicleType(Integer routeNumber, String vehicleType,
			Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}
}
