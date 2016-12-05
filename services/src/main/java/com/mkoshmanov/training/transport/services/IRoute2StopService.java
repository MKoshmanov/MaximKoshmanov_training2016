package com.mkoshmanov.training.transport.services;

import java.util.List;

import com.mkoshmanov.training.transport.daodb.customentity.TransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route2Stop;

public interface IRoute2StopService extends IGenericService<Route2Stop> {
	
	/**
     * @param id
     * @return list routes pass through particular stop
     */
	List<TransportStopAndRoute> routesThroughStop(Long id);

}
