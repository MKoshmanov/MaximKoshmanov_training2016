package com.mkoshmanov.training.transport.services;

import java.util.List;
import java.util.Locale;

import org.springframework.transaction.annotation.Transactional;

import com.mkoshmanov.training.transport.datamodel.Stop;

public interface IStopService {

	Stop getById(Long id, Locale locale);

	@Transactional
	Long save(Stop entity, Locale locale);

	@Transactional
	void saveAll(List<Stop> entity, Locale locale);

	@Transactional
	void delete(Long id);

	List<Stop> getAll(Locale locale);

	@Transactional
	void update(Stop entity, Locale locale);

}
