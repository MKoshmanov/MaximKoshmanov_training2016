package com.mkoshmanov.training.transport.web.converter;

import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.datamodel.Route;
import com.mkoshmanov.training.transport.web.model.RouteDTO;

@Component
public class RouteConverterImpl implements IConverter<Route, RouteDTO> {

	@Override
	public Route dto2Entity(RouteDTO dto) {
		Route route = new Route();
		route.setId(dto.getId());
		route.setNumber(dto.getNumber());
		route.setName(dto.getName());
		return route;
	}

	@Override
	public RouteDTO entity2Dto(Route entity) {
		RouteDTO routeDTO = new RouteDTO();
		routeDTO.setId(entity.getId());
		routeDTO.setNumber(entity.getNumber());
		routeDTO.setName(entity.getName());
		return routeDTO;
	}

}
