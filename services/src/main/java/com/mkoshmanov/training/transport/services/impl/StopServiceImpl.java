package com.mkoshmanov.training.transport.services.impl;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daoapi.IStopDao;
import com.mkoshmanov.training.transport.datamodel.Stop;
import com.mkoshmanov.training.transport.services.IStopService;

@Service
public class StopServiceImpl implements IStopService {

	@Inject
	private IStopDao stopDao;

	@Override
	public Stop getById(Long id, Locale locale) {
		return stopDao.getById(id, locale);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(Long id) {
		stopDao.deleteById(id);
	}

	@Override
	public List<Stop> getAll(Locale locale) {
		return stopDao.getAll(locale);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void update(Stop entity, Locale locale) {
		stopDao.update(entity, locale);
	}

	@Override
	public void saveAll(List<Stop> stops, Locale locale) {
		for (Stop stop : stops) {
			save(stop, locale);
		}
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Long save(Stop stop, Locale locale) {
		if (stop.getId() == null) {
			Long id = stopDao.insert(stop, locale);
			return id;
		} else {
			stopDao.update(stop, locale);
			return stop.getId();
		}
	}
}
