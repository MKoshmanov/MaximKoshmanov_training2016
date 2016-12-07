package com.mkoshmanov.training.transport.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.services.IDriverService;
import com.mkoshmanov.training.transport.services.components.UserDataStorage;
import com.mkoshmanov.training.transport.web.converter.DriverConverterImpl;
import com.mkoshmanov.training.transport.web.model.DriverDTO;

@RestController
@RequestMapping("/driver")
public class DriverController {

	//private static final Logger LOGGER = LoggerFactory.getLogger(DriverController.class);
	
	@Inject
	private ApplicationContext context;

	@Inject
	private IDriverService service;
	
	@Inject
	private DriverConverterImpl driverConverterImpl; 
		
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DriverDTO>> getAll() {
		/*LOGGER.info("1.2.3");
		LOGGER.error("sss");*/
		UserDataStorage userDataStorage = context.getBean(UserDataStorage.class);
        System.out.println("DriverController:" + userDataStorage);
				List<Driver> drivers = service.getAll();

		List<DriverDTO> converted = new ArrayList<>();
		for (Driver driver : drivers) {
			converted.add(driverConverterImpl.entity2Dto(driver));
			
		}
		return new ResponseEntity<List<DriverDTO>>(converted, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/free", method = RequestMethod.GET)
	public ResponseEntity<List<DriverDTO>> getAllFreeDrivers() {
		UserDataStorage userDataStorage = context.getBean(UserDataStorage.class);
        System.out.println("DriverController:" + userDataStorage);
				List<Driver> drivers = service.getAllFreeDrivers();

		List<DriverDTO> converted = new ArrayList<>();
		for (Driver driver : drivers) {
			converted.add(driverConverterImpl.entity2Dto(driver));
			
		}
		return new ResponseEntity<List<DriverDTO>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/{driverId}", method = RequestMethod.GET)
	public ResponseEntity<DriverDTO> getById(@PathVariable Long driverId) {
		Driver driver = service.getById(driverId);
		return new ResponseEntity<DriverDTO>(driverConverterImpl.entity2Dto(driver), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createNewDriver(@RequestBody DriverDTO driverDTO) {
		service.save(driverConverterImpl.dto2Entity(driverDTO));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{driverId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateDriver(@RequestBody DriverDTO driverDTO, @PathVariable Long driverId) {
		Driver driver = driverConverterImpl.dto2Entity(driverDTO);
		driver.setId(driverId);
		service.update(driver);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{driverId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long driverId) {
		service.delete(driverId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
}
