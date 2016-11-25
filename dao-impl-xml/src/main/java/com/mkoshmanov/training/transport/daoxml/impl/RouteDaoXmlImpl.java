package com.mkoshmanov.training.transport.daoxml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IRouteDao;
import com.mkoshmanov.training.transport.daodb.customentity.PublicTransportStopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route;

@Repository
public class RouteDaoXmlImpl implements IRouteDao {

	@Override
	public Route getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long insert(Route entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Route entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Route> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PublicTransportStopAndRoute> stopsOnRoute(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
