package com.mkoshmanov.training.transport.web.converter;

import com.mkoshmanov.training.transport.datamodel.Transport;
import com.mkoshmanov.training.transport.web.model.TransportDTO;

public class TransportConverterImpl implements IConverter<Transport, TransportDTO> {

	@Override
	public Transport dto2Entity(TransportDTO dto) {
		Transport transport = new Transport();
		transport.setId(dto.getId());
		transport.setVehicleType(dto.getVehicleType());
		transport.setDriverId(dto.getDriverId());
		transport.setRouteId(dto.getRouteId());
		return transport;
	}

	@Override
	public TransportDTO entity2Dto(Transport entity) {
		TransportDTO transportDTO = new TransportDTO();
		transportDTO.setId(entity.getId());
		transportDTO.setVehicleType(entity.getVehicleType());
		transportDTO.setDriverId(entity.getDriverId());
		transportDTO.setRouteId(entity.getRouteId());
		return transportDTO;
	}

}
