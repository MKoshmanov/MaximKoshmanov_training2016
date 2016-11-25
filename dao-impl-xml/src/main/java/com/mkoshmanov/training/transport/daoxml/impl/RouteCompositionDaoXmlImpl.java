package com.mkoshmanov.training.transport.daoxml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IRouteCompositionDao;
import com.mkoshmanov.training.transport.daodb.customentity.PublicTransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.RouteComposition;

@Repository
public class RouteCompositionDaoXmlImpl implements IRouteCompositionDao {

	@Override
	public RouteComposition getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long insert(RouteComposition entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(RouteComposition entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RouteComposition> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PublicTransportStopAndRoute> countRoutesThroughStop(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
