package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daoapi.ITransportDao;
import com.mkoshmanov.training.transport.datamodel.Transport;
import com.mkoshmanov.training.transport.services.ITransportService;

@Service
public class TransportServiceImpl implements ITransportService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransportServiceImpl.class);

	@Inject
	private ITransportDao transportDao;

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
			LOGGER.info("Ready new transport. id={}, type={}, registration_number={}, driver_id={}, route_id={}",
					transport.getId(), transport.getType(), transport.getRegistrationNumber(), transport.getDriverId(),
					transport.getRouteId());
			return id;
		} else {
			transportDao.update(transport);
			return transport.getId();
		}
	}

	@Override
	public Transport get(Long id) {
		return transportDao.getById(id);
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
