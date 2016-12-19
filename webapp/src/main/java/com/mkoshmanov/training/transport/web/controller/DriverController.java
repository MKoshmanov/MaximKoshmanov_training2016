package com.mkoshmanov.training.transport.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.services.IDriverService;
import com.mkoshmanov.training.transport.web.converter.DTOToDriverConverter;
import com.mkoshmanov.training.transport.web.converter.DriverToDTOConverter;
import com.mkoshmanov.training.transport.web.exception.BaseException;
import com.mkoshmanov.training.transport.web.exception.ErrorResponse;
import com.mkoshmanov.training.transport.web.model.DriverDTO;

@RestController
@RequestMapping("/driver")
public class DriverController {

	@Inject
	private IDriverService service;
	@Inject
	private DriverToDTOConverter driverToDtoConverter;
	@Inject
	private DTOToDriverConverter dtoToDriverConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DriverDTO>> getAll() {
		List<Driver> drivers = service.getAll();
		List<DriverDTO> converted = new ArrayList<>();
		for (Driver driver : drivers) {
			converted.add(driverToDtoConverter.convert(driver));
		}
		return new ResponseEntity<List<DriverDTO>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/{driverId}", method = RequestMethod.GET)
	public ResponseEntity<DriverDTO> getById(@PathVariable Long driverId) throws BaseException {
		Driver driver = service.getById(driverId);
		if (driver != null) {
			return new ResponseEntity<DriverDTO>(driverToDtoConverter.convert(driver), HttpStatus.OK);
		} else {
			throw new BaseException("Driver not exist");
		}
	}

	@RequestMapping(value = "/free", method = RequestMethod.GET)
	public ResponseEntity<List<DriverDTO>> getAllFreeDrivers() {
		List<Driver> drivers = service.getAllFreeDrivers();
		List<DriverDTO> converted = new ArrayList<>();
		for (Driver driver : drivers) {
			converted.add(driverToDtoConverter.convert(driver));
		}
		return new ResponseEntity<List<DriverDTO>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/busy", method = RequestMethod.GET)
	public ResponseEntity<List<DriverDTO>> getAllBusyDrivers() {
		List<Driver> drivers = service.getAllBusyDrivers();
		List<DriverDTO> converted = new ArrayList<>();
		for (Driver driver : drivers) {
			converted.add(driverToDtoConverter.convert(driver));
		}
		return new ResponseEntity<List<DriverDTO>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/route/{transportRouteNumber}/{transportVehicleType}", method = RequestMethod.GET)
	public ResponseEntity<List<DriverDTO>> getDriversOnParticularRoute(@PathVariable Integer transportRouteNumber,
			@PathVariable String transportVehicleType) {

		List<Driver> drivers = service.getDriversOnParticularRoute(transportRouteNumber, transportVehicleType);
		List<DriverDTO> converted = new ArrayList<>();
		for (Driver driver : drivers) {
			converted.add(driverToDtoConverter.convert(driver));
		}
		return new ResponseEntity<List<DriverDTO>>(converted, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createNewDriver(@RequestBody DriverDTO driverDTO) throws BaseException {
		checkStringFormat(driverDTO);
		service.save(dtoToDriverConverter.convert(driverDTO));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{driverId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateDriver(@RequestBody DriverDTO driverDTO, @PathVariable Long driverId)
			throws BaseException {
		checkStringFormat(driverDTO);
		Driver driver = dtoToDriverConverter.convert(driverDTO);
		driver.setId(driverId);
		service.update(driver);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{driverId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long driverId) throws BaseException {
		Driver driver = service.getById(driverId);
		if (driver != null) {
			service.delete(driverId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			throw new BaseException("Driver not exist");
		}
	}

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

	private void checkStringFormat(DriverDTO driverDTO) throws BaseException {
		String firstName = driverDTO.getFirstName();
		String lastName = driverDTO.getLastName();
		if (StringUtils.isNumeric(firstName) || StringUtils.isNumeric(lastName)) {
			throw new BaseException("The specified first name or last name can't contain numeric symbols");
		}
	}
}
