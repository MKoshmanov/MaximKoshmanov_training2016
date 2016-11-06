package com.mkoshmanov.training.transport.daodb;

import java.util.List;

import com.mkoshmanov.training.transport.datamodel.Transport;

public interface TransportDao {

    Transport get(Long id);

    Long insert(Transport entity);

    void update(Transport entity);

    void delete(Long id);

    List<Transport> getAll();
}
