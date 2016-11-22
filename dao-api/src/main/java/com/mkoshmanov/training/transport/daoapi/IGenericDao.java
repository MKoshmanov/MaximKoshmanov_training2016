package com.mkoshmanov.training.transport.daoapi;


import java.util.List;

interface IGenericDao<T> {
	
	T getById(Long id);

	Long insert(T entity);

	void update(T entity);

	void delete(Long id);

	List<T> getAll();
}
