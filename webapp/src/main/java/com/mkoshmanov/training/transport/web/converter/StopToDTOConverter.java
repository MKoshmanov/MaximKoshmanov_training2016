package com.mkoshmanov.training.transport.web.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.datamodel.Stop;
import com.mkoshmanov.training.transport.web.model.StopDTO;

@Component
public class StopToDTOConverter implements Converter<Stop, StopDTO> {

	@Override
	public StopDTO convert(Stop stop) {
		StopDTO stopDTO = new StopDTO();
		stopDTO.setId(stop.getId());
		stopDTO.setName(stop.getName());
		return stopDTO;
	}
}
