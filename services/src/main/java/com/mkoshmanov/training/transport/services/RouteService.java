package com.mkoshmanov.training.transport.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mkoshmanov.training.transport.datamodel.Route;

public interface RouteService {

	@Transactional
    void saveAll(List<Route> routes);

    Long save(Route route);

    Route get(Long id);
    
    List<Route> getAll();
    
    void delete(Long id);
	
}
