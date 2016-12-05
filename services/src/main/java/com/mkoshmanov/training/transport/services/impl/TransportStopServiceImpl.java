package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daoapi.ITransportStopDao;
import com.mkoshmanov.training.transport.datamodel.TransportStop;
import com.mkoshmanov.training.transport.services.ITransportStopService;

@Service
public class TransportStopServiceImpl extends GenericServiceImpl<TransportStop> implements ITransportStopService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimetableServiceImpl.class);

	@Inject
	private ITransportStopDao transportStopDao;

	@Override
	public void saveAll(List<TransportStop> transportStops) {
		for (TransportStop transportStop : transportStops) {
			save(transportStop);
		}
	}

	@Override
	public Long save(TransportStop transportStop) {
		if (transportStop.getId() == null) {
			Long id = transportStopDao.insert(transportStop);
			LOGGER.info("Transport stop created: id = {}, name = {}", transportStop.getId(),
					transportStop.getName());
			return id;
		} else {
			transportStopDao.update(transportStop);
			return transportStop.getId();
		}
	}
}
