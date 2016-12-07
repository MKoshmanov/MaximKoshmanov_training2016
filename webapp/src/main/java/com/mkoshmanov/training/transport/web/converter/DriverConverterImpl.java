package com.mkoshmanov.training.transport.web.converter;

import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.datamodel.Driver;
import com.mkoshmanov.training.transport.web.model.DriverDTO;

@Component
public class DriverConverterImpl implements IConverter<Driver, DriverDTO> {

	@Override
	public Driver dto2Entity(DriverDTO dto) {
		Driver driver = new Driver();
		driver.setId(dto.getId());
		driver.setFirstName(dto.getFirstName());
		driver.setLastName(dto.getLastName());
		driver.setBirthday(dto.getBirthday());
		driver.setLicenseCategory(dto.getLicenceCategory());
		return driver;
	}

	@Override
	public DriverDTO entity2Dto(Driver entity) {
		DriverDTO driverDTO = new DriverDTO();
		driverDTO.setId(entity.getId());
		driverDTO.setFirstName(entity.getFirstName());
		driverDTO.setLastName(entity.getLastName());
		driverDTO.setBirthday(entity.getBirthday());
		driverDTO.setLicenceCategory(entity.getLicenseCategory());
		return driverDTO;
	}
}
