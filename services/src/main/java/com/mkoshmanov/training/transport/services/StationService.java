package com.mkoshmanov.training.transport.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mkoshmanov.training.transport.datamodel.Station;

public interface StationService {

	@Transactional
    void saveAll(List<Station> stations);

    Long save(Station station);

    Station get(Long id);
    
    List<Station> getAll();
    
    void delete(Long id);
	
}
