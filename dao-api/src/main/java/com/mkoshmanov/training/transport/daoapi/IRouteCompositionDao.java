package com.mkoshmanov.training.transport.daoapi;

import java.util.List;

import com.mkoshmanov.training.transport.daodb.customentity.PublicTransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.RouteComposition;

public interface IRouteCompositionDao extends IGenericDao<RouteComposition> {

	List<PublicTransportStopAndRoute> countRoutesThroughStop(Long id);

}
