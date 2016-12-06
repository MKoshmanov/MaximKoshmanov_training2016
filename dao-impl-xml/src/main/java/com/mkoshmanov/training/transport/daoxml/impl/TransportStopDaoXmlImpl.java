package com.mkoshmanov.training.transport.daoxml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.ITransportStopDao;
import com.mkoshmanov.training.transport.datamodel.TransportStop;

@Repository
public class TransportStopDaoXmlImpl extends GenericDaoXxlImpl<TransportStop> implements ITransportStopDao {

	@Override
	public Class<TransportStop> getClassName() {
		return TransportStop.class;
	}

	@Override
	public String getTableName() {
		return TransportStop.class.getSimpleName().toLowerCase();
	}
	
	@Override
	public void update(TransportStop entity) {
		List<TransportStop> transportStops = readCollection();
		for (TransportStop transportStop : transportStops) {
			if (transportStop.getId().equals(entity.getId())) {
				transportStop.setName(entity.getName());
				break;
			}
			else {
				insert(entity);
			}
		}
		writeCollection(transportStops);
	}

}
