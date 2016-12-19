package com.mkoshmanov.training.transport.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.mkoshmanov.training.transport.daoapi.IGenericDao;
import com.mkoshmanov.training.transport.services.IGenericService;

@Service
public abstract class GenericServiceImpl<T> implements IGenericService<T> {

	@Inject
	private IGenericDao<T> genericDao;

	@Override
	public T getById(Long id) {
		if (genericDao.getById(id) != null) {
			return genericDao.getById(id);
		}
		return null;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(Long id) {
		genericDao.deleteById(id);
	}

	@Override
	public List<T> getAll() {
		return genericDao.getAll();
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@CacheEvict(value = "getById", key = "#entity")
	public void update(T entity) {
		genericDao.update(entity);
	}
}
