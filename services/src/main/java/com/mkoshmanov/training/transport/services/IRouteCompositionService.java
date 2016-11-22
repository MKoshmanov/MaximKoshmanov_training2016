package com.mkoshmanov.training.transport.services;

import java.util.List;

import com.mkoshmanov.training.transport.daodb.customentity.PublicTransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.RouteComposition;

public interface IRouteCompositionService extends IGenericService<RouteComposition> {
	
	/**
     * @param id
     * @return list routes pass through particular stop
     */
	List<PublicTransportStopAndRoute> routesThroughStop(Long id);

}
