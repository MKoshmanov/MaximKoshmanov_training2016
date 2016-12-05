package com.mkoshmanov.training.transport.daoapi;

import java.util.List;

import com.mkoshmanov.training.transport.daodb.customentity.TransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route2Stop;

public interface IRoute2StopDao extends IGenericDao<Route2Stop> {

	List<TransportStopAndRoute> countRoutesThroughStop(Long id);

}
