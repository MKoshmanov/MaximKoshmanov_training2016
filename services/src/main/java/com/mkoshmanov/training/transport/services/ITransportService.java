package com.mkoshmanov.training.transport.services;

import java.util.List;
import java.util.Locale;

import com.mkoshmanov.training.transport.datamodel.Stop;
import com.mkoshmanov.training.transport.datamodel.Transport;

public interface ITransportService extends IGenericService<Transport> {

	List<Transport> getAllRouteByVehicleType (String vehicleType);
	
	List<Stop> getStopsOnRouteById(Long id, Locale locale);

}

