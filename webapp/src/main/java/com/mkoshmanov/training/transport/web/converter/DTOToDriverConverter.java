package com.mkoshmanov.training.transport.web.converter;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.services.ITransportService;
import com.mkoshmanov.training.transport.web.model.DriverDTO;

@Component
public class DTOToDriverConverter implements Converter<DriverDTO, Driver> {

	@Inject
	private ITransportService transportService;
	@Override
	public Driver convert(DriverDTO driverDTO) {
		Driver driver = new Driver();
		driver.setId(driverDTO.getId());
		driver.setFirstName(driverDTO.getFirstName());
		driver.setLastName(driverDTO.getLastName());
		driver.setBirthday(driverDTO.getBirthday());
		driver.setTransport(transportService.getById(driverDTO.getTransportId()));
		return driver;
	}
}
