package com.mkoshmanov.training.transport.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mkoshmanov.training.transport.datamodel.Transport;

public interface TransportService {

	@Transactional
    void saveAll(List<Transport> transports);

    Long save(Transport driver);

    Transport get(Long id);
    
    List<Transport> getAll();
    
    void delete(Long id);
   
}

