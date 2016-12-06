package com.mkoshmanov.training.transport.web.converter;

import com.mkoshmanov.training.transport.datamodel.Timetable;
import com.mkoshmanov.training.transport.web.model.TimetableDTO;

public class TimetableConverterImpl implements IConverter<Timetable, TimetableDTO>{

	@Override
	public Timetable dto2Entity(TimetableDTO dto) {
		Timetable timetable = new Timetable();
		timetable.setId(dto.getId());
		timetable.setTransportStopId(dto.getTransportStopId());
		timetable.setRouteId(dto.getRouteId());
		timetable.setArrivalTime(dto.getArrivalTime());
		return timetable;
	}

	@Override
	public TimetableDTO entity2Dto(Timetable entity) {
		TimetableDTO timetableDTO = new TimetableDTO();
		timetableDTO.setId(entity.getId());
		timetableDTO.setTransportStopId(entity.getTransportStopId());
		timetableDTO.setRouteId(entity.getRouteId());
		timetableDTO.setArrivalTime(entity.getArrivalTime());
		return timetableDTO;
	}

}
