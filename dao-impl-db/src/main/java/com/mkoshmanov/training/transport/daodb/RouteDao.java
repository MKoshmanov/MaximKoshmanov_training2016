package com.mkoshmanov.training.transport.daodb;

import java.util.List;

import com.mkoshmanov.training.transport.daodb.customentity.StopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route;

public interface RouteDao extends GenericDao<Route> {
	
	List<StopAndRoute> stopsOnRoute(Long id);
}