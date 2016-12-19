package com.mkoshmanov.training.transport.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mkoshmanov.training.transport.datamodel.Stop;
import com.mkoshmanov.training.transport.services.IStopService;
import com.mkoshmanov.training.transport.web.converter.DTOToStopConverter;
import com.mkoshmanov.training.transport.web.converter.StopToDTOConverter;
import com.mkoshmanov.training.transport.web.exception.BaseException;
import com.mkoshmanov.training.transport.web.exception.ErrorResponse;
import com.mkoshmanov.training.transport.web.exception.StopException;
import com.mkoshmanov.training.transport.web.model.StopDTO;

@RestController
@RequestMapping("/stop")
public class StopController {

	@Inject
	private IStopService stopService;
	@Inject
	private StopToDTOConverter stopToDTOConverter;
	@Inject
	private DTOToStopConverter dtoToStopConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StopDTO>> getAll() {
		Locale locale = LocaleContextHolder.getLocale();
		List<Stop> stops = stopService.getAll(locale);
		List<StopDTO> converted = new ArrayList<>();
		for (Stop stop : stops) {
			converted.add(stopToDTOConverter.convert(stop));
		}
		return new ResponseEntity<List<StopDTO>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/{stopId}", method = RequestMethod.GET)
	public ResponseEntity<StopDTO> getById(@PathVariable Long stopId) throws StopException, BaseException {
		Locale locale = LocaleContextHolder.getLocale();
		Stop stop = stopService.getById(stopId, locale);
		if (stop != null) {
			return new ResponseEntity<StopDTO>(stopToDTOConverter.convert(stop), HttpStatus.OK);
		} else {
			throw new BaseException("Stop not exist");
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createNewStop(@RequestBody StopDTO stopDTO) throws BaseException {
		Locale locale = LocaleContextHolder.getLocale();
		checkNameFormat(stopDTO);
		stopService.save(dtoToStopConverter.convert(stopDTO), locale);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{stopId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateStop(@RequestBody StopDTO stopDTO, @PathVariable Long stopId) throws BaseException {
		Locale locale = LocaleContextHolder.getLocale();
		checkNameFormat(stopDTO);
		Stop stop = dtoToStopConverter.convert(stopDTO);
		stop.setId(stopId);
		stopService.update(stop, locale);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{stopId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long stopId) throws BaseException {
		Stop stop = stopService.getById(stopId, Locale.ENGLISH);
		if (stop != null) {
			stopService.delete(stopId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			throw new BaseException("This stop not exist");
		}
	}

	@ExceptionHandler(BaseException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

	private void checkNameFormat(StopDTO stopDTO) throws BaseException {
		String name = stopDTO.getName();
		if (StringUtils.isNumeric(name)) {
			throw new BaseException("The specified name is not a sure stop format");
		}
	}
}
