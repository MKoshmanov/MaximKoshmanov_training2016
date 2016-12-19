package com.mkoshmanov.training.transport.daoapi;

import java.util.List;

import com.mkoshmanov.training.transport.datamodel.RouteStation;

public interface IRouteStationDao extends IGenericDao<RouteStation> {
	
	List<RouteStation> getRouteStationsByTransportId (Long transportId);

}