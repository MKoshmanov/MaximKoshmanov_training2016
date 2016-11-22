package com.mkoshmanov.training.transport.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface IGenericService<T> {
	
	@Transactional
	T get(Long id);

	Long save(T entity);

	void saveAll(List<T> entity);

	void delete(Long id);

	List<T> getAll();
}
