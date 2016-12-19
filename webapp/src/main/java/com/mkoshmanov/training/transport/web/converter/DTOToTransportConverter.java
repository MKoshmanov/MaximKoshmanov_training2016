package com.mkoshmanov.training.transport.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.datamodel.Transport;
import com.mkoshmanov.training.transport.web.model.TransportDTO;

@Component
public class DTOToTransportConverter implements Converter<TransportDTO, Transport> {

	@Override
	public Transport convert(TransportDTO transportDTO) {
		Transport transport = new Transport();
		transport.setId(transportDTO.getId());
		transport.setVehicleType(transportDTO.getVehicleType());
		transport.setRouteNumber(transportDTO.getRouteNumber());
		transport.setRouteName(transportDTO.getRouteName());
		return transport;
	}
}
