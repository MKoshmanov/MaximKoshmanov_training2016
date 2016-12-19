package com.mkoshmanov.training.transport.daoapi;

import java.util.List;
import java.util.Locale;

import com.mkoshmanov.training.transport.datamodel.Stop;
import com.mkoshmanov.training.transport.datamodel.Transport;

public interface ITransportDao extends IGenericDao<Transport> {

	List<Transport> getAllTransportByVehicleType (String vehicleType);
	
	List<Stop> getStopsOnRouteById(Long id, Locale locale);
	
	List<Stop> getStopsOnRouteByRouteNumberAndVehicleType(Integer routeNumber, String vehicleType, Locale locale);
}
