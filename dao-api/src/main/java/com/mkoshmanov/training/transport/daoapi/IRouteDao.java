package com.mkoshmanov.training.transport.daoapi;

import java.util.List;

import com.mkoshmanov.training.transport.daodb.customentity.PublicTransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route;

public interface IRouteDao extends IGenericDao<Route> {
	
	List<PublicTransportStopAndRoute> stopsOnRoute(Long id);

}