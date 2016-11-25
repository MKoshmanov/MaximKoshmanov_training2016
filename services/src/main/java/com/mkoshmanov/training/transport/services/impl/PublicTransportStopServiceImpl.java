package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daoapi.IPublicTransportStopDao;
import com.mkoshmanov.training.transport.datamodel.PublicTransportStop;
import com.mkoshmanov.training.transport.services.IPublicTransportStopService;

@Service
public class PublicTransportStopServiceImpl extends GenericServiceImpl<PublicTransportStop> implements IPublicTransportStopService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TimetableServiceImpl.class);

	@Inject
	private IPublicTransportStopDao publicTransportStopDao;

	@Override
	public void saveAll(List<PublicTransportStop> publicTransportStops) {
		for (PublicTransportStop publicTransportStop : publicTransportStops) {
			save(publicTransportStop);
		}
	}

	@Override
	public Long save(PublicTransportStop publicTransportStop) {
		if (publicTransportStop.getId() == null) {
			Long id = publicTransportStopDao.insert(publicTransportStop);
			LOGGER.info("Public transport stop created: id = {}, name = {}", publicTransportStop.getId(),
					publicTransportStop.getName());
			return id;
		} else {
			publicTransportStopDao.update(publicTransportStop);
			return publicTransportStop.getId();
		}
	}
}
