package com.mkoshmanov.training.transport.daodb;

import java.util.List;

import com.mkoshmanov.training.transport.datamodel.Stop;

public interface StopDao {

	Stop get(Long id);

	Long insert(Stop entity);

	void update(Stop entity);

	void delete(Long id);

	List<Stop> getAll();
	
}
