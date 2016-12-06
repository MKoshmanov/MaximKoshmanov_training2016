package com.mkoshmanov.training.transport.web.converter;

import org.springframework.stereotype.Component;

import com.mkoshmanov.training.transport.datamodel.TransportStop;
import com.mkoshmanov.training.transport.web.model.TransportStopDTO;

@Component
public class TransportStopConverterImpl implements IConverter<TransportStop, TransportStopDTO> {

	@Override
	public TransportStop dto2Entity(TransportStopDTO dto) {
		TransportStop transportStop = new TransportStop();
		transportStop.setId(dto.getId());
		transportStop.setName(dto.getName());
		return transportStop;
	}

	@Override
	public TransportStopDTO entity2Dto(TransportStop entity) {
		TransportStopDTO transportStopDTO = new TransportStopDTO();
		transportStopDTO.setId(entity.getId());
		transportStopDTO.setName(entity.getName());
		return transportStopDTO;
	}

}
