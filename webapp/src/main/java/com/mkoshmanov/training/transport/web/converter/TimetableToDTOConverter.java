package com.mkoshmanov.training.transport.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.datamodel.Timetable;
import com.mkoshmanov.training.transport.web.model.TimetableDTO;

@Component
public class TimetableToDTOConverter implements Converter<Timetable, TimetableDTO> {

	@Override
	public TimetableDTO convert(Timetable timetable) {
		TimetableDTO timetableDTO = new TimetableDTO();
		timetableDTO.setId(timetable.getId());
		timetableDTO.setArrivalTime(timetable.getArrivalTime());
		return timetableDTO;
	}
}
