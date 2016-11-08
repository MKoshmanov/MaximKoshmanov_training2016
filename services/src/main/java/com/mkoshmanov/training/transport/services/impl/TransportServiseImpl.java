package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daodb.TransportDao;
import com.mkoshmanov.training.transport.datamodel.Transport;
import com.mkoshmanov.training.transport.services.TransportService;

@Service
public class TransportServiseImpl implements TransportService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DriverServiceImpl.class);

	@Inject
	private TransportDao transportDao;

	@Override
	public void saveAll(List<Transport> transports) {
		for (Transport transport : transports) {
			save(transport);
		}
	}

	@Override
	public Long save(Transport transport) {
		if (transport.getId() == null) {
			Long id = transportDao.insert(transport);
			LOGGER.info("Ready new transport. id={}, vehicle={}, registration_number={}, type={}, driver_id={}, route_id={}",
					transport.getId(), transport.getVehicle(), transport.getRegistrationNumber(), transport.getType(),
					transport.getDriverId(), transport.getRouteId());
			return id;
		} else {
			transportDao.update(transport);
			return transport.getId();
		}
	}

	@Override
	public Transport get(Long id) {
		return transportDao.get(id);
	}

	@Override
	public void delete(Long id) {
		transportDao.delete(id);
	}

	@Override
	public List<Transport> getAll() {
		return transportDao.getAll();
	}

}
