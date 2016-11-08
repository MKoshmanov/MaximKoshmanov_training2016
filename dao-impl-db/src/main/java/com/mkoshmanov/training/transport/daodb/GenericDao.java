package com.mkoshmanov.training.transport.daodb;


import java.util.List;

interface GenericDao<T> {
	
	T get(Long id);

	Long insert(T entity);

	void update(T entity);

	void delete(Long id);

	List<T> getAll();
}
