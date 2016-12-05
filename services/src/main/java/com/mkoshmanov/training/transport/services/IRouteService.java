package com.mkoshmanov.training.transport.services;

import java.util.List;

import com.mkoshmanov.training.transport.daodb.customentity.TransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route;

public interface IRouteService extends IGenericService<Route> {

	/**
	 * @param id
	 * @return list stops which are located on particular route
	 */
	List<TransportStopAndRoute> stopsOnRoute(Long id);

}
