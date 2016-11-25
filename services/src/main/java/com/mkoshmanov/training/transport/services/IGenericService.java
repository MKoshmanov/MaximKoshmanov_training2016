package com.mkoshmanov.training.transport.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface IGenericService<T> {

	T getById(Long id);

	@Transactional
	Long save(T entity);

	@Transactional
	void saveAll(List<T> entity);

	@Transactional
	void delete(Long id);
	
	List<T> getAll();

}
