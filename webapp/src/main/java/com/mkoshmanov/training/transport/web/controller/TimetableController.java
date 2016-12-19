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

import com.mkoshmanov.training.transport.datamodel.Timetable;
import com.mkoshmanov.training.transport.services.ITimetableService;
import com.mkoshmanov.training.transport.web.converter.DTOToTimetableConverter;
import com.mkoshmanov.training.transport.web.converter.TimetableToDTOConverter;
import com.mkoshmanov.training.transport.web.exception.BaseException;
import com.mkoshmanov.training.transport.web.exception.ErrorResponse;
import com.mkoshmanov.training.transport.web.model.TimetableDTO;

@RestController
@RequestMapping("/timetable")
public class TimetableController {

	@Inject
	private ITimetableService timetableService;
	@Inject
	private TimetableToDTOConverter timetableToDTOConverter;
	@Inject
	private DTOToTimetableConverter dtoToTimetableConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<TimetableDTO>> getAll() {
		List<Timetable> timetables = timetableService.getAll();
		List<TimetableDTO> converted = new ArrayList<>();
		for (Timetable sttimetableop : timetables) {
			converted.add(timetableToDTOConverter.convert(sttimetableop));
		}
		return new ResponseEntity<List<TimetableDTO>>(converted, HttpStatus.OK);
	}

	@RequestMapping(value = "/{timetableId}", method = RequestMethod.GET)
	public ResponseEntity<TimetableDTO> getById(@PathVariable Long timetableId) throws BaseException {
		Timetable timetable = timetableService.getById(timetableId);
		if (timetable != null) {
			return new ResponseEntity<TimetableDTO>(timetableToDTOConverter.convert(timetable), HttpStatus.OK);
		} else {
			throw new BaseException("Timetable not exist");
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody TimetableDTO timetableDTO) {
		timetableService.save(dtoToTimetableConverter.convert(timetableDTO));
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{timetableId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateTimetable(@RequestBody TimetableDTO timetableDTO,
			@PathVariable Long timetableId) {
		Timetable timetable = dtoToTimetableConverter.convert(timetableDTO);
		timetable.setId(timetableId);
		timetableService.update(timetable);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{timetableId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long timetableId) throws BaseException {
		Timetable timetable = timetableService.getById(timetableId);
		if (timetable != null) {
			timetableService.delete(timetableId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			throw new BaseException("This timetable not exist");
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
