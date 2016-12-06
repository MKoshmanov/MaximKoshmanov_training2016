package com.mkoshmanov.training.transport.web.converter;

import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.datamodel.Route2Stop;
import com.mkoshmanov.training.transport.web.model.Route2StopDTO;

@Component
public class Route2StopConverterImpl implements IConverter<Route2Stop, Route2StopDTO> {

	@Override
	public Route2Stop dto2Entity(Route2StopDTO dto) {
		Route2Stop route2Stop = new Route2Stop();
		route2Stop.setId(dto.getId());
		route2Stop.setTransportStopId(dto.getTransportStopId());
		route2Stop.setRouteId(dto.getRouteId());
		route2Stop.setSequence(dto.getSequence());
		return route2Stop;
	}

	@Override
	public Route2StopDTO entity2Dto(Route2Stop entity) {
		Route2StopDTO route2StopDTO = new Route2StopDTO();
		route2StopDTO.setId(entity.getId());
		route2StopDTO.setTransportStopId(entity.getTransportStopId());
		route2StopDTO.setRouteId(entity.getRouteId());
		route2StopDTO.setSequence(entity.getSequence());
		return route2StopDTO;
	}

}
