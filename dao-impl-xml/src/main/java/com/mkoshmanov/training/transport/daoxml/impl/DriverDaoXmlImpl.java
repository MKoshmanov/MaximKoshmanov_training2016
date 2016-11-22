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

import com.mkoshmanov.training.transport.daoapi.IDriverDao;
import com.mkoshmanov.training.transport.daodb.customentity.DriversOnRoute;
import com.mkoshmanov.training.transport.datamodel.Driver;
import com.thoughtworks.xstream.XStream;

@Repository
public class DriverDaoXmlImpl implements IDriverDao {

	private XStream xstream;
	private File file;

	@Value("${basePath}")
	private String basePath;

	@PostConstruct
	private void intialize() throws IOException {
		// TODO move to the parent class
		// TODO refactoring: use classname instead of hardcoded filename
		xstream = new XStream();
		xstream.alias("driver", Driver.class);

		file = new File(basePath + "/books.xml");
		if (!file.exists()) {
			file.createNewFile();
			xstream.toXML(new ArrayList<>(), new FileOutputStream(file));
		}
	}

	private List<Driver> readCollection() {
		return (List<Driver>) xstream.fromXML(file);
	}
	
	private void writeCollection(List<Driver> newList) {
        try {
            xstream.toXML(newList, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);// TODO custom exception
        }
    }

    private long getNextId(List<Driver> allDrivers) {
        return allDrivers.isEmpty() ? 1l : allDrivers.get(
                allDrivers.size() - 1).getId() + 1;
    }


	@Override
	public Driver getById(Long id) {
		List<Driver> allDrivers = readCollection();

		for (Driver driver : allDrivers) {
			if (driver.getId().equals(id)) {
				return driver;
			}
		}
		return null;
	}

	@Override
	public Long insert(Driver entity) {
		List<Driver> allDrivers = readCollection();
        Long id = getNextId(allDrivers);

        allDrivers.add(entity);

        entity.setId(new Long(id));

        writeCollection(allDrivers);
        return id;
	}

	@Override
	public void update(Driver entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		 List<Driver> allDrivers = readCollection();

	        List<Driver> newList = new ArrayList<>();
	        // TODO: don't iterate whole collection
	        for (Driver driver : allDrivers) {
	            if (!driver.getId().equals(id)) {
	                newList.add(driver);
	            }
	        }
	        writeCollection(newList);

	}

	@Override
	public List<Driver> getAll() {
		return readCollection();
	}

	@Override
	public List<DriversOnRoute> getDriversOnParticularRoute(Integer number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DriversOnRoute> getAllBusyDrivers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DriversOnRoute> getAllFreeDrivers() {
		// TODO Auto-generated method stub
		return null;
	}

}
