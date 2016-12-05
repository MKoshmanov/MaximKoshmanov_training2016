package com.mkoshmanov.training.transport.daoxml.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mkoshmanov.training.transport.daoapi.IGenericDao;
import com.mkoshmanov.training.transport.datamodel.AbstractModel;
import com.mkoshmanov.training.transport.datamodel.utils.Table;
import com.thoughtworks.xstream.XStream;

@Repository
public abstract class GenericDaoXxlImpl<T extends AbstractModel> implements IGenericDao<T> {

	private XStream xstream;
	private File file;
	private Class<T> entityClass;
	private String tableName;

	@Value("${basePath}")
	private String basePath;

	public abstract Class<T> getClassName();

	public GenericDaoXxlImpl() {
		entityClass = getClassName();
		tableName = entityClass.getAnnotation(Table.class).name();
	}

	@PostConstruct
	private void intialize() throws IOException {
		xstream = new XStream();
		xstream.alias(tableName, entityClass);
		file = new File(basePath + "/" + tableName + ".xml");
		if (!file.exists()) {
			file.createNewFile();
			xstream.toXML(new ArrayList<>(), new FileOutputStream(file));
		}
	}

	protected List<T> readCollection() {
		return (List<T>) xstream.fromXML(file);
	}

	protected void writeCollection(List<T> newList) {
		try {
			xstream.toXML(newList, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);// TODO custom exception
		}
	}

	private long getNextId(List<T> all) {
		return all.isEmpty() ? 1l : all.get(all.size() - 1).getId() + 1;
	}

	@Override
	public T getById(Long id) {
		List<T> all = readCollection();
		for (T entity : all) {
			if (entity.getId().equals(id)) {
				return entity;
			}
		}
		return null;
	}

	@Override
	public Long insert(T entity) {
		List<T> all = readCollection();
		Long id = getNextId(all);
		all.add(entity);
		entity.setId(new Long(id));
		writeCollection(all);
		return id;
	}
	
	@Override
	public void deleteById(Long id) {
		List<T> all = readCollection();
		for (int i = 0; i < all.size(); i++) {
			if (all.get(i).getId().equals(id)) {
				all.remove(i);
				break;
			}
		}
		writeCollection(all);
	}

	@Override
	public List<T> getAll() {
		return readCollection();
	}
}
