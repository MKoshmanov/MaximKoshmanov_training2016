package com.mkoshmanov.training.transport.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daoapi.IRouteStationDao;
import com.mkoshmanov.training.transport.daoapi.ITransportDao;
import com.mkoshmanov.training.transport.datamodel.RouteStation;
import com.mkoshmanov.training.transport.datamodel.Stop;
import com.mkoshmanov.training.transport.datamodel.Transport;
import com.mkoshmanov.training.transport.services.ITransportService;

@Service
public class TransportServiceImpl extends GenericServiceImpl<Transport> implements ITransportService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransportServiceImpl.class);

	@Inject
	private IRouteStationDao routeStationDao;
	@Inject
	private ITransportDao transportDao;

	@Override
	public void saveAll(List<Transport> transports) {
		for (Transport transport : transports) {
			save(transport);
		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Long save(Transport transport) {
		if (transport.getId() == null) {
			Long id = transportDao.insert(transport);
			LOGGER.info("Transport: id = {}, vehicle type = {}, route number = {}, route name = {}, ",
					transport.getId(), transport.getVehicleType(), transport.getRouteNumber(),
					transport.getRouteName());
			return id;
		} else {
			transportDao.update(transport);
			return transport.getId();
		}
	}

	@Override
	public List<Transport> getAllRouteByVehicleType(String vehicleType) {
		return transportDao.getAllTransportByVehicleType(vehicleType);
	}

	@Override
	public List<Stop> getStopsOnRouteById(Long id, Locale locale) {
		List<RouteStation> routeStations = routeStationDao.getRouteStationsByTransportId(id);
		Collections.sort(routeStations, new Comparator<RouteStation>() {
		    @Override
		    public int compare(RouteStation o1, RouteStation o2) {
		        return o1.getSequence().compareTo(o2.getSequence());
		    }
		});
		List<Stop> stops = new ArrayList<>();
		for(RouteStation routeStation : routeStations) {
			Stop stop = routeStation.getStop();
			stops.add(stop);
		}
		return stops;
	}
}
