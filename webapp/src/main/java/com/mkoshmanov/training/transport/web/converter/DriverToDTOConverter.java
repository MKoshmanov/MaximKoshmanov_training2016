package com.mkoshmanov.training.transport.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.web.model.DriverDTO;

@Component
public class DriverToDTOConverter implements Converter<Driver, DriverDTO> {

	@Override
	public DriverDTO convert(Driver entity) {
		DriverDTO driverDTO = new DriverDTO();
		driverDTO.setId(entity.getId());
		driverDTO.setFirstName(entity.getFirstName());
		driverDTO.setLastName(entity.getLastName());
		driverDTO.setBirthday(entity.getBirthday());
		driverDTO.setTransportId(entity.getTransport().getId());
		return driverDTO;
	}
}
