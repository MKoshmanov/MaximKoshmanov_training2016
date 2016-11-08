package com.mkoshmanov.training.transport.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mkoshmanov.training.transport.daodb.customentity.StopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Route;

public interface RouteService {

	@Transactional
    void saveAll(List<Route> routes);

    Long save(Route route);

    Route get(Long id);
    
    List<Route> getAll();
    
    void delete(Long id);
    
    /**
     * @param id
     * @return list stops which are located on particular route
     */
    List<StopAndRoute> stopsOnRoute (Long id);
    	
}
