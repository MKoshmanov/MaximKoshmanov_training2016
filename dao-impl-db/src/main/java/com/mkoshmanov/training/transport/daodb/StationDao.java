package com.mkoshmanov.training.transport.daodb;

import java.util.List;

import com.mkoshmanov.training.transport.daodb.customentity.StopAndRoute;
import com.mkoshmanov.training.transport.datamodel.Station;

public interface StationDao extends GenericDao<Station> {

	List<StopAndRoute> countRoutesThroughStop(Long id);
}
