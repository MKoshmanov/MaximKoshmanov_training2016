package com.mkoshmanov.training.transport.web.converter;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.datamodel.RouteStation;
import com.mkoshmanov.training.transport.services.IStopService;
import com.mkoshmanov.training.transport.services.ITimetableService;
import com.mkoshmanov.training.transport.services.ITransportService;
import com.mkoshmanov.training.transport.web.model.RouteStationDTO;

@Component
public class DTOToRouteStatiomConverter implements Converter<RouteStationDTO, RouteStation> {

	@Inject
	private ITransportService transportService;

	@Inject
	private IStopService stopService;

	@Inject
	private ITimetableService timetableService;

	@Override
	public RouteStation convert(RouteStationDTO dto) {
		RouteStation routeStation = new RouteStation();
		routeStation.setId(dto.getId());
		routeStation.setStop(stopService.getById(dto.getStopId(), Locale.ENGLISH));
		routeStation.setTransportId(dto.getTransportId());
		routeStation.setSequence(dto.getSequence());
		routeStation.setTimetable(timetableService.getById(dto.getTimetableId()));
		return routeStation;
	}
}
