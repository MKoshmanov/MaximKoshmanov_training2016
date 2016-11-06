package com.mkoshmanov.training.transport.daodb;

import java.util.List;

import com.mkoshmanov.training.transport.datamodel.Route;

public interface RouteDao {
	
	Route get(Long id);

	Long insert(Route entity);

	void update(Route entity);

	void delete(Long id);

	List<Route> getAll();
}