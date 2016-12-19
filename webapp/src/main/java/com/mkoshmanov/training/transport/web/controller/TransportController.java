package com.mkoshmanov.training.transport.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mkoshmanov.training.transport.datamodel.Stop;
import com.mkoshmanov.training.transport.datamodel.Transport;
import com.mkoshmanov.training.transport.services.ITransportService;
import com.mkoshmanov.training.transport.web.converter.DTOToTransportConverter;
import com.mkoshmanov.training.transport.web.converter.StopToDTOConverter;
import com.mkoshmanov.training.transport.web.converter.TransportToDTOConverter;
import com.mkoshmanov.training.transport.web.exception.BaseException;
import com.mkoshmanov.training.transport.web.exception.ErrorResponse;
import com.mkoshmanov.training.transport.web.model.StopDTO;
import com.mkoshmanov.training.transport.web.model.TransportDTO;

@RestController
@RequestMapping("/transport")
public class TransportController {

	@Inject
	private ITransportService transportService;
	@Inject
	private TransportToDTOConverter transportToDTOConverter;
	@Inject
	private DTOToTransportConverter dtoToTransportConverter;
	@Inject
	private StopToDTOConverter stopToDTOConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TransportDTO>> getAll() {
		List<Transport> transports = transportService.getAll();
		List<TransportDTO> converted = new ArrayList<>();
		for (Transport transport : transports) {
			converted.add(transportToDTOConverter.convert(transport));
		}
		return new ResponseEntity<List<TransportDTO>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/type/{vehicleType}", method = RequestMethod.GET)
	public ResponseEntity<List<TransportDTO>> getRouteByVehicleType(@PathVariable String vehicleType) throws BaseException {
		if(vehicleType.equals("Bus") || vehicleType.equals("Trolleybus")) {
		List<Transport> transports = transportService.getAllRouteByVehicleType(vehicleType);
		List<TransportDTO> converted = new ArrayList<>();
		for (Transport transport : transports) {
			converted.add(transportToDTOConverter.convert(transport));
		}
		return new ResponseEntity<List<TransportDTO>>(converted, HttpStatus.OK);
		} else {
			throw new BaseException("This type not exist");
		}
	}

	@RequestMapping(value = "/route/stops/{transportId}", method = RequestMethod.GET)
	public ResponseEntity<List<StopDTO>> getStopsOnRouteById(@PathVariable Long transportId) throws BaseException {
		Locale locale = LocaleContextHolder.getLocale();
		Transport transport = transportService.getById(transportId);
		if (transport != null) {
			List<Stop> stops = transportService.getStopsOnRouteById(transportId, locale);
			List<StopDTO> converted = new ArrayList<>();
			for (Stop stop : stops) {
				converted.add(stopToDTOConverter.convert(stop));
			}
			return new ResponseEntity<List<StopDTO>>(converted, HttpStatus.OK);
		} else {
			throw new BaseException("This route not exist");
		}
	}

	@RequestMapping(value = "/{transportId}", method = RequestMethod.GET)
	public ResponseEntity<TransportDTO> getById(@PathVariable Long transportId) throws BaseException {
		Transport transport = transportService.getById(transportId);
		if (transport != null) {
			return new ResponseEntity<TransportDTO>(transportToDTOConverter.convert(transport), HttpStatus.OK);
		} else {
			throw new BaseException("Transport not exist");
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createNewTransport(@RequestBody TransportDTO transportDTO) throws BaseException {
		checkStringFormat(transportDTO);
		transportService.save(dtoToTransportConverter.convert(transportDTO));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{transportId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateTransport(@RequestBody TransportDTO transportDTO,
			@PathVariable Long transportId) throws BaseException {
		checkStringFormat(transportDTO);
		Transport transport = dtoToTransportConverter.convert(transportDTO);
		transport.setId(transportId);
		transportService.update(transport);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{transportId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteTransport(@PathVariable Long transportId) throws BaseException {
		Transport transport = transportService.getById(transportId);
		if (transport != null) {
			transportService.delete(transportId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			throw new BaseException("This transport not exist");
		}
	}

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
	
	private void checkStringFormat(TransportDTO transportDTO) throws BaseException {
		String vehicleType = transportDTO.getVehicleType();
		String routeName = transportDTO.getRouteName();
		if(StringUtils.isNumeric(vehicleType)) {
			throw new BaseException("The specified vehicle type is not a sure transport format");
		}
		if(StringUtils.isNumeric(routeName)) {
			throw new BaseException("The specified route name is not a sure transport format");
		}
	}
}
