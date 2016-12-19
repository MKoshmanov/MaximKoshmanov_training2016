package com.mkoshmanov.training.transport.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.datamodel.RouteStation;
import com.mkoshmanov.training.transport.web.model.RouteStationDTO;

@Component
public class RouteStationToDTOConverter implements Converter<RouteStation, RouteStationDTO> {

	@Override
	public RouteStationDTO convert(RouteStation routeStation) {
		RouteStationDTO routeStationDTO = new RouteStationDTO();
		routeStationDTO.setId(routeStation.getId());
		routeStationDTO.setStopId(routeStation.getStop().getId());
		routeStationDTO.setTransportId(routeStation.getTransportId());
		routeStationDTO.setSequence(routeStation.getSequence());
		routeStationDTO.setTimetableId(routeStation.getTimetable().getId());
		return routeStationDTO;
	}
}
