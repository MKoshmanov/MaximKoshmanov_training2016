package com.mkoshmanov.training.transport.daodb;

import java.util.List;

import com.mkoshmanov.training.transport.datamodel.Station;

public interface StationDao {

	Station get(Long id);

	Long insert(Station entity);

	void update(Station entity);

	void delete(Long id);

	List<Station> getAll();
}
