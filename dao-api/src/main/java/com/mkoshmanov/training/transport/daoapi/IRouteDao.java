package com.mkoshmanov.training.transport.daoapi;

import java.util.List;

import com.mkoshmanov.training.transport.daodb.customentity.TransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route;

public interface IRouteDao extends IGenericDao<Route> {
	
	List<TransportStopAndRoute> stopsOnRoute(Long id);

}