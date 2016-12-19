package com.mkoshmanov.training.transport.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.datamodel.Transport;
import com.mkoshmanov.training.transport.web.model.TransportDTO;

@Component
public class TransportToDTOConverter implements Converter<Transport, TransportDTO> {

	@Override
	public TransportDTO convert(Transport transport) {
		TransportDTO transportDTO = new TransportDTO();
		transportDTO.setId(transport.getId());
		transportDTO.setVehicleType(transport.getVehicleType());
		transportDTO.setRouteNumber(transport.getRouteNumber());
		transportDTO.setRouteName(transport.getRouteName());
		return transportDTO;
	}
}
