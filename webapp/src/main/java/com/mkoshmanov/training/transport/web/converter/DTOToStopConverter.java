package com.mkoshmanov.training.transport.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.datamodel.Stop;
import com.mkoshmanov.training.transport.web.model.StopDTO;

@Component
public class DTOToStopConverter implements Converter<StopDTO, Stop> {

	@Override
	public Stop convert(StopDTO stopDTO) {
		Stop stop = new Stop();
		stop.setId(stopDTO.getId());
		stop.setName(stopDTO.getName());
		return stop;
	}
}
