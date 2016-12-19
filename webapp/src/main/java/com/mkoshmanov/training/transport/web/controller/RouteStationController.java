package com.mkoshmanov.training.transport.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mkoshmanov.training.transport.datamodel.RouteStation;
import com.mkoshmanov.training.transport.services.IRouteStationService;
import com.mkoshmanov.training.transport.web.converter.DTOToRouteStatiomConverter;
import com.mkoshmanov.training.transport.web.converter.RouteStationToDTOConverter;
import com.mkoshmanov.training.transport.web.exception.BaseException;
import com.mkoshmanov.training.transport.web.exception.ErrorResponse;
import com.mkoshmanov.training.transport.web.model.RouteStationDTO;

@RestController
@RequestMapping("/routeStation")
public class RouteStationController {

	@Inject
	private IRouteStationService routeStationService;
	@Inject
	private RouteStationToDTOConverter routeStationToDTOConverter;
	@Inject
	private DTOToRouteStatiomConverter dtoToRouteStationConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<RouteStationDTO>> getAll() {
		List<RouteStation> routeStations = routeStationService.getAll();
		List<RouteStationDTO> converted = new ArrayList<>();
		for (RouteStation routeStation : routeStations) {
			converted.add(routeStationToDTOConverter.convert(routeStation));
		}
		return new ResponseEntity<List<RouteStationDTO>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/{routeStationId}", method = RequestMethod.GET)
	public ResponseEntity<RouteStationDTO> getById(@PathVariable Long routeStationId) throws BaseException {
		RouteStation routeStation = routeStationService.getById(routeStationId);
		if (routeStation != null) {
			return new ResponseEntity<RouteStationDTO>(routeStationToDTOConverter.convert(routeStation), HttpStatus.OK);
		} else {
			throw new BaseException("Route station not exist");
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody RouteStationDTO routeStationDTO) throws BaseException {
		if(routeStationService.save(dtoToRouteStationConverter.convert(routeStationDTO)) != null) {
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			throw new BaseException("Route station can not be created");
		}
	}

	@RequestMapping(value = "/{routeStationId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateRouteStation(@RequestBody RouteStationDTO routeStationDTO,
			@PathVariable Long routeStationId) {
		RouteStation routeStation = dtoToRouteStationConverter.convert(routeStationDTO);
		routeStation.setId(routeStationId);
		routeStationService.update(routeStation);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{routeStationId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long routeStationId) throws BaseException {
		RouteStation routeStation = routeStationService.getById(routeStationId);
		if (routeStation != null) {
			routeStationService.delete(routeStationId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			throw new BaseException("This route station not exist");
		}
	}

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
}
