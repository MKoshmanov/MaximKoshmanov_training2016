package com.mkoshmanov.training.transport.daoxml.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IStopDao;
import com.mkoshmanov.training.transport.datamodel.Stop;

@Repository
public class StopDaoXmlImpl extends GenericDaoXxlImpl<Stop> implements IStopDao {

	@Override
	public Class<Stop> getClassName() {
		return Stop.class;
	}

	@Override
	public String getTableName() {
		return Stop.class.getSimpleName().toLowerCase();
	}
	
	@Override
	public void update(Stop entity) {
		List<Stop> transportStops = readCollection();
		for (Stop transportStop : transportStops) {
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

	@Override
	public Stop getById(Long id, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long insert(Stop entity, Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Stop entity, Locale locale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Stop> getAll(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

}
