package com.mkoshmanov.training.transport.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.datamodel.Timetable;
import com.mkoshmanov.training.transport.web.model.TimetableDTO;

@Component
public class DTOToTimetableConverter implements Converter<TimetableDTO, Timetable> {

	@Override
	public Timetable convert(TimetableDTO dto) {
		Timetable timetable = new Timetable();
		timetable.setId(dto.getId());
		timetable.setArrivalTime(dto.getArrivalTime());
		return timetable;
	}
}