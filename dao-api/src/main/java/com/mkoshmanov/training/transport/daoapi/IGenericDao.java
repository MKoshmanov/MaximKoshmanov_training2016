package com.mkoshmanov.training.transport.daoapi;


import java.util.List;

public interface IGenericDao<T> {
	
	T getById(Long id);

	Long insert(T entity);

	void update(T entity);

	void deleteById(Long id);

	List<T> getAll();

}
