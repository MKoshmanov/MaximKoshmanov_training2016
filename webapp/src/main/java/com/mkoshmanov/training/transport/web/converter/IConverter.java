package com.mkoshmanov.training.transport.web.converter;

public interface IConverter <Entity, DTO> {

	Entity dto2Entity (DTO dto);
	
	DTO entity2Dto (Entity entity);
}
