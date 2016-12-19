package com.mkoshmanov.training.transport.daoapi;

import java.util.List;
import java.util.Locale;

import com.mkoshmanov.training.transport.datamodel.Stop;

public interface IStopDao {

	Stop getById(Long id, Locale locale);

	Long insert(Stop entity, Locale locale);

	void update(Stop entity, Locale locale);

	void deleteById(Long id);

	List<Stop> getAll(Locale locale);
	
}
